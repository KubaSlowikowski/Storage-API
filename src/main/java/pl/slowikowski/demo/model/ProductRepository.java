package pl.slowikowski.demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Page<Product> findAll(Pageable page);

    Optional<Product> findById(Integer id);

    Product save(Product entity);

    boolean existsById(Integer id);
}
