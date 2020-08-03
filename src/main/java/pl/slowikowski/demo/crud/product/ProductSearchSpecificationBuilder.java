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

    public ProductSearchSpecificationBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification result = new ProductSearchSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new ProductSearchSpecification(params.get(i)))
                    : Specification.where(result).and(new ProductSearchSpecification(params.get(i)));
        }

        return result;
    }
}
