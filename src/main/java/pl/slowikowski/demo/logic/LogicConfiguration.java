package pl.slowikowski.demo.logic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.model.ProductGroupRepository;
import pl.slowikowski.demo.model.ProductRepository;

@Configuration
class LogicConfiguration {

    @Bean
    ProductService productService(
            final ProductRepository repository
    ) {
        return new ProductService(repository);
    }

    @Bean
    ProductGroupService productGroupService(
            final ProductGroupRepository repository
    ) {
        return new ProductGroupService(repository);
    }
}
