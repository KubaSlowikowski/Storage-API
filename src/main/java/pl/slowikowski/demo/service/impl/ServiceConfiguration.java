package pl.slowikowski.demo.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.mapper.ProductGroupMapper;
import pl.slowikowski.demo.mapper.ProductMapper;
import pl.slowikowski.demo.repository.ProductGroupRepository;
import pl.slowikowski.demo.repository.ProductRepository;

@Configuration
public class ServiceConfiguration {

    @Bean
    ProductServiceImpl productService(final ProductRepository repository, final ProductGroupRepository groupRepository, final ProductMapper productMapper) {
        return new ProductServiceImpl(repository, groupRepository, productMapper);
    }

    @Bean
    ProductGroupServiceImpl productGroupService(final ProductGroupRepository repository, final ProductGroupMapper groupMapper) {
        return new ProductGroupServiceImpl(repository, groupMapper);
    }
}
