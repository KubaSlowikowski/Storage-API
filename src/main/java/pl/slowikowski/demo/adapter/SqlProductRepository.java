package pl.slowikowski.demo.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.model.Product;
import pl.slowikowski.demo.model.ProductRepository;

@Repository
interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer> {

}
