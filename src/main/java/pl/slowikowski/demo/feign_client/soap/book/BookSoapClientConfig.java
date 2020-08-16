package pl.slowikowski.demo.feign_client.soap.book;

import com.raglis.library_api.soap.books.BookPortType;
import com.raglis.library_api.soap.books.ObjectFactory;
import com.raglis.library_api.soap.readers.ReaderPortType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BookSoapClientConfig {

    private final String address = "http://s0208:8082/soap-api/books";

    @Bean(name = "bookProxy")
    public BookPortType bookServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(BookPortType.class);
        jaxWsProxyFactoryBean.setAddress(this.address);
        return (BookPortType) jaxWsProxyFactoryBean.create();
    }

    @Bean("bookObjectFactory")
    public ObjectFactory readerObjectFactory() {
        return new ObjectFactory();
    }
}
