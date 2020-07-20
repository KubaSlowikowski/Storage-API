package pl.slowikowski.demo.product;

import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.abstraction.CommonRepository;

import java.util.List;

@Repository
public interface ProductRepository extends CommonRepository<Product> {

    List<Product> findAllByGroup_Id(Integer groupId);
}
