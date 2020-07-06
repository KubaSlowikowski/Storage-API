package pl.slowikowski.demo.model;

import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository {
    List<ProductGroup> findAll();

    Optional<ProductGroup> findById(Integer id);

    ProductGroup save(ProductGroup entity);

    boolean existsById(Integer id);
}
