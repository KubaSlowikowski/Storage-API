package pl.slowikowski.demo.feign_client.soap.reader;

import com.raglis.library_api.soap.readers.ObjectFactory;
import com.raglis.library_api.soap.readers.ReaderPortType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReaderSoapClientConfig {

    private final String address = "http://s0208:8082/soap-api/readers";

    @Bean(name = "readerProxy")
    public ReaderPortType bookServiceProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(ReaderPortType.class);
        jaxWsProxyFactoryBean.setAddress(this.address);
        return (ReaderPortType) jaxWsProxyFactoryBean.create();
    }

    @Bean("readerObjectFactory")
    public ObjectFactory clientObjectFactory() {
        return new ObjectFactory();
    }
}
