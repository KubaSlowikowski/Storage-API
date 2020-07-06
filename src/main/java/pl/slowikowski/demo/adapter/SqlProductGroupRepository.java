package pl.slowikowski.demo.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.persistence.model.ProductGroup;
import pl.slowikowski.demo.persistence.repository.ProductGroupRepository;

@Repository
interface SqlProductGroupRepository extends ProductGroupRepository, JpaRepository<ProductGroup, Integer> {
}
