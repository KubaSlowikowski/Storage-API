package pl.slowikowski.demo.model;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    Product save(Product entity);

    boolean existsById(Integer id);
}
