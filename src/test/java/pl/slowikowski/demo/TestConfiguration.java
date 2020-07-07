package pl.slowikowski.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.persistence.repository.ProductRepository;

import java.lang.reflect.Field;
import java.util.*;

@Configuration
class TestConfiguration {

    @Bean
    @Primary
    @Profile("integration")
    ProductRepository testRepo() {
        return new ProductRepository() {
            private Map<Integer, Product> products = new HashMap<>();

            @Override
            public List<Product> findAllByGroup_Id(Integer groupId) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return new ArrayList<>(products.values());
            }

            @Override
            public Page<Product> findAll(Pageable page) {
                return null;
            }

            @Override
            public Optional<Product> findById(Integer id) {
                return Optional.ofNullable(products.get(id));
            }

            @Override
            public Product save(Product entity) {
                int key = products.size() + 1;
                Field field = null;
                try {
                    field = Task.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                products.put(key, entity);
                return products.get(key);
            }

            @Override
            public boolean existsById(Integer id) {
                return products.containsKey(id);
            }
        };
    }
}