package pl.slowikowski.demo.persistence.repository;

import pl.slowikowski.demo.persistence.model.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {

    List<Product> findAllByGroup_Id(Integer groupId);
}
