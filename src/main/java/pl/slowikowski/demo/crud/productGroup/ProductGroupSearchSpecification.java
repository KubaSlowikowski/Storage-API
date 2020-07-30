package pl.slowikowski.demo.crud.productGroup;

import pl.slowikowski.demo.crud.abstraction.AbstractSearchSpecification;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;

public class ProductGroupSearchSpecification extends AbstractSearchSpecification<ProductGroup> {
    public ProductGroupSearchSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
