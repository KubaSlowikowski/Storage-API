package pl.slowikowski.demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository {
    List<ProductGroup> findAll();

    Page<ProductGroup> findAll(Pageable page);

    Optional<ProductGroup> findById(Integer id);

    ProductGroup save(ProductGroup entity);

    boolean existsById(Integer id);
}
