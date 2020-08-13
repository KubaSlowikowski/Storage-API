//package pl.slowikowski.demo.feign_client.soap.book;
//
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class BookSoapClientConfig {
//
//    private final String address = "http://s0208:8082/soap-api/books";
//
//    @Bean(name = "bookProxy")
//    public BookPortType bookServiceProxy() {
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactoryBean.setServiceClass(BookPortType.class);
//        jaxWsProxyFactoryBean.setAddress(this.address);
//
//    }
//
//}
