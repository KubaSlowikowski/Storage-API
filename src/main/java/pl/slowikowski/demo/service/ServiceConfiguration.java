package pl.slowikowski.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.persistence.repository.ProductGroupRepository;
import pl.slowikowski.demo.persistence.repository.ProductRepository;

@Configuration
class ServiceConfiguration {

    @Bean
    ProductRepositoryImpl productService(
            final ProductRepository repository,
            final ProductGroupRepository groupRepository
    ) {
        return new ProductRepositoryImpl(repository, groupRepository);
    }

    @Bean
    ProductGroupRepositoryImpl productGroupService(
            final ProductGroupRepository repository
    ) {
        return new ProductGroupRepositoryImpl(repository);
    }
}