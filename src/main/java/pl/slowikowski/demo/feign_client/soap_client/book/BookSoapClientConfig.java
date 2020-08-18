package pl.slowikowski.demo.feign_client.soap_client.book;

import com.raglis.library_api.soap.books.BookPortType;
import com.raglis.library_api.soap.books.ObjectFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.feign_client.LibraryProperties;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryClient;
import pl.slowikowski.demo.feign_client.soap_client.AuthSoapInterceptor;

@Configuration
class BookSoapClientConfig {

    private final String address;
    private final AuthLibraryClient authClient;
    private LibraryProperties libraryProperties;

    BookSoapClientConfig(final AuthLibraryClient authClient, final LibraryProperties libraryProperties) {
        this.authClient = authClient;
        this.libraryProperties = libraryProperties;
        this.address = libraryProperties.getUrl() + "/soap-api/books";
    }

    @Bean(name = "bookProxy")
    public BookPortType bookServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(BookPortType.class);
        jaxWsProxyFactoryBean.setAddress(this.address);

        BookPortType serviceClient = (BookPortType) jaxWsProxyFactoryBean.create();
        Client proxy = ClientProxy.getClient(serviceClient);

        var interceptor = new AuthSoapInterceptor(authClient, libraryProperties);
        proxy.getOutInterceptors().add(interceptor);

        return serviceClient;
    }

    @Bean("bookObjectFactory")
    public ObjectFactory readerObjectFactory() {
        return new ObjectFactory();
    }
}
