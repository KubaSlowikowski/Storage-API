package pl.slowikowski.demo.soap;

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
}
