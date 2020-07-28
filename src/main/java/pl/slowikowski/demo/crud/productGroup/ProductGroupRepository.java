package pl.slowikowski.demo.crud.productGroup;

import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.crud.abstraction.CommonRepository;

@Repository
public interface ProductGroupRepository extends CommonRepository<ProductGroup> {
}
