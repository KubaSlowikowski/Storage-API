package pl.slowikowski.demo.soap;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import pl.slowikowski.demo.soap.product.ProductSoapController;
import pl.slowikowski.demo.soap.productGroup.ProductGroupSoapController;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    public WebServiceConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
//        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    //    PRODUCT:
    @Bean(name = "products")
    public SimpleWsdl11Definition productWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/product.wsdl"));
        return wsdl11Definition;
    }

    //    PRODUCT GROUP:
    @Bean(name = "productGroups")
    public SimpleWsdl11Definition productGroupWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/productGroup.wsdl"));
        return wsdl11Definition;
    }


    //VERSION WITH SERVICES (WITHOUT ENDPOINTS
    private final ApplicationContext applicationContext;

    @Bean
    public ServletRegistrationBean<CXFServlet> disServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
    }

    @Bean(name = "productClientEndpoint")
    public EndpointImpl productEndpoint(ProductGroupSoapController groupService) {
        Bus bus = applicationContext.getBean(SpringBus.class);
        EndpointImpl endpoint = new EndpointImpl(bus, groupService);
        endpoint.publish("/group");
        return endpoint;
    }

    @Bean(name = "productGroupClientEndpoint")
    public EndpointImpl productGroupEndpoint(ProductSoapController productService) {
        Bus bus = applicationContext.getBean(SpringBus.class);
        EndpointImpl endpoint = new EndpointImpl(bus, productService);
        endpoint.publish("/product");
        return endpoint;
    }
}
