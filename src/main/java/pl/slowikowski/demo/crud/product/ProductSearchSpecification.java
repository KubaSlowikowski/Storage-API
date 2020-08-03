package pl.slowikowski.demo.crud.product;

import pl.slowikowski.demo.crud.abstraction.AbstractSearchSpecification;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;

public class ProductSearchSpecification extends AbstractSearchSpecification<Product> {
    private SearchCriteria criteria;

    public ProductSearchSpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }
}
