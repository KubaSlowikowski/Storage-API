package pl.slowikowski.demo.crud.product;

import org.springframework.data.jpa.domain.Specification;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;
import pl.slowikowski.demo.crud.abstraction.SearchOperation;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchSpecificationBuilder /*extends AbstractSearchSpecificationBuilder<Product>*/ {

    private List<SearchCriteria> params;

    public ProductSearchSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final ProductSearchSpecificationBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public ProductSearchSpecificationBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value, orPredicate));
        }
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }
        Specification<Product> result = new ProductSearchSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new ProductSearchSpecification(params.get(i)))
                    : Specification.where(result).and(new ProductSearchSpecification(params.get(i)));
        }
        return result;
    }

    public final ProductSearchSpecificationBuilder with(ProductSearchSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final ProductSearchSpecificationBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
