package pl.slowikowski.demo.soap;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.annotation.PostConstruct;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

//    PRODUCT:
    @Bean(name = "products")
    public DefaultWsdl11Definition productWsdl11Definition(XsdSchema productsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://jakub.slowikowski.pl/soap-example/product");
        wsdl11Definition.setSchema(productsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema productsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schema/product.xsd"));
    }


//    PRODUCT GROUP:
    @Bean(name = "productGroups")
    public DefaultWsdl11Definition productGroupWsdl11Definition(XsdSchema productGroupsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductGroupPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://jakub.slowikowski.pl/soap-example/product-group");
        wsdl11Definition.setSchema(productGroupsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema productGroupsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schema/productGroup.xsd"));
    }


    //VERSION WITH SERVICES (WITHOUT ENDPOINTS
    private static final String BINDING_URI = "http://www.w3.org/2003/05/soap/bindings/HTTP/";

    @Autowired
    private List<SoapService> endpoints;

    @Bean
    public ServletRegistrationBean<CXFServlet> disServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus bus = new SpringBus();
        bus.setProperty("org.apache.cxf.stax.maxTextLength", 1024 * 1024 * 1024);
        return bus;
    }

    @PostConstruct
    public void init() {
        for (SoapService bean : endpoints) {
            if (bean.getClass().getAnnotation(SoapEndpoint.class) == null) {
                throw new IllegalArgumentException("Missed @SoapEndpoint for " + bean.getClass().getName());
            }
            EndpointImpl endpoint = new EndpointImpl(springBus(), bean);
            endpoint.setBindingUri(BINDING_URI);
            endpoint.publish(bean.getClass().getAnnotation(SoapEndpoint.class).publish());
        }
    }
}
