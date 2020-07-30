package pl.slowikowski.demo.crud.product;

import pl.slowikowski.demo.crud.abstraction.AbstractSearchSpecification;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;

public class ProductSearchSpecification extends AbstractSearchSpecification<Product> {
    public ProductSearchSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
