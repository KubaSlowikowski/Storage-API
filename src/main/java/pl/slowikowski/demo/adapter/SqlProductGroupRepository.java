package pl.slowikowski.demo.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowikowski.demo.model.ProductGroup;
import pl.slowikowski.demo.model.ProductGroupRepository;

interface SqlProductGroupRepository extends ProductGroupRepository, JpaRepository<ProductGroup, Integer> {
}
