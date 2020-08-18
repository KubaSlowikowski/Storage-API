package pl.slowikowski.demo.feign_client.soap_client.reader;

import com.raglis.library_api.soap.readers.ObjectFactory;
import com.raglis.library_api.soap.readers.ReaderPortType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.feign_client.LibraryProperties;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryClient;
import pl.slowikowski.demo.feign_client.soap_client.AuthSoapInterceptor;

@Configuration
class ReaderSoapClientConfig {

    private final String address;
    private final AuthLibraryClient authClient;
    private LibraryProperties libraryProperties;

    ReaderSoapClientConfig(final AuthLibraryClient authClient, final LibraryProperties libraryProperties) {
        this.authClient = authClient;
        this.libraryProperties = libraryProperties;
        this.address = libraryProperties.getUrl() + "/soap-api/readers";
    }


    @Bean(name = "readerProxy")
    public ReaderPortType bookServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(ReaderPortType.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        ReaderPortType serviceClient = (ReaderPortType) jaxWsProxyFactoryBean.create();
        Client proxy = ClientProxy.getClient(serviceClient);

        var interceptor = new AuthSoapInterceptor(authClient, libraryProperties);
        proxy.getOutInterceptors().add(interceptor);

        return serviceClient;
    }

    @Bean("readerObjectFactory")
    public ObjectFactory clientObjectFactory() {
        return new ObjectFactory();
    }
}
