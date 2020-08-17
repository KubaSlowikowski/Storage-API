package pl.slowikowski.demo.feign_client.soap_client.reader;

import com.raglis.library_api.soap.readers.ObjectFactory;
import com.raglis.library_api.soap.readers.ReaderPortType;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryClient;
import pl.slowikowski.demo.feign_client.soap_client.AuthSoapInterceptor;

@Configuration
@RequiredArgsConstructor
class ReaderSoapClientConfig {

    private final String address = "http://s0208:8082/soap-api/readers";
    private final AuthLibraryClient authClient;

    @Bean(name = "readerProxy")
    public ReaderPortType bookServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(ReaderPortType.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        ReaderPortType serviceClient = (ReaderPortType) jaxWsProxyFactoryBean.create();
        Client proxy = ClientProxy.getClient(serviceClient);

        var interceptor = new AuthSoapInterceptor(authClient);
        proxy.getOutInterceptors().add(interceptor);

        return serviceClient;
//        ReaderPortType serviceClient = (ReaderPortType) jaxWsProxyFactoryBean.create();
//
//        // Get the underlying Client object from the proxy object of service interface
//        Client proxy = ClientProxy.getClient(serviceClient);
//
//        var authRequest = new AuthLibraryRequest();
//        authRequest.setUsername("admin");
//        authRequest.setPassword("adminadmin");
//        var token = authClient.authenticateUser(authRequest).getToken();
//
//        // Creating HTTP headers
//        Map<String, List<String>> headers = new HashMap<>();
//        headers.put("Authorization", Collections.singletonList("Bearer " + token));
//
//        // Add HTTP headers to the web service request
//        proxy.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
//
//        return serviceClient;
    }

    @Bean("readerObjectFactory")
    public ObjectFactory clientObjectFactory() {
        return new ObjectFactory();
    }
}
