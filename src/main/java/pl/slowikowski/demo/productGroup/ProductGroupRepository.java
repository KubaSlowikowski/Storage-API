package pl.slowikowski.demo.productGroup;

import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.abstraction.CommonRepository;

@Repository
public interface ProductGroupRepository extends CommonRepository<ProductGroup> {
}
