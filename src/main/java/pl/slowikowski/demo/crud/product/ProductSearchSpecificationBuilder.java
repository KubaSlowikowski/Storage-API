package pl.slowikowski.demo.crud.product;

import org.springframework.data.jpa.domain.Specification;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSearchSpecificationBuilder /*extends AbstractSearchSpecificationBuilder<Product>*/ {
    private final List<SearchCriteria> params;

    public ProductSearchSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public ProductSearchSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(ProductSearchSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
