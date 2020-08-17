package pl.slowikowski.demo.feign_client.soap_client.book;

import com.raglis.library_api.soap.books.BookPortType;
import com.raglis.library_api.soap.books.ObjectFactory;
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
class BookSoapClientConfig {

    private final String address = "http://s0208:8082/soap-api/books";
    private final AuthLibraryClient authClient;

    @Bean(name = "bookProxy")
    public BookPortType bookServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(BookPortType.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        BookPortType serviceClient = (BookPortType) jaxWsProxyFactoryBean.create();
        Client proxy = ClientProxy.getClient(serviceClient);

        var interceptor = new AuthSoapInterceptor(authClient);
        proxy.getOutInterceptors().add(interceptor);

        return serviceClient;

//        BookPortType serviceClient = (BookPortType) jaxWsProxyFactoryBean.create();
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

    @Bean("bookObjectFactory")
    public ObjectFactory readerObjectFactory() {
        return new ObjectFactory();
    }
}
