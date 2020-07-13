package pl.slowikowski.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.entity.ProductGroup;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {
}
