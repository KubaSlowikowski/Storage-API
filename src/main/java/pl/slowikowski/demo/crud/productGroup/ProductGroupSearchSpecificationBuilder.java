package pl.slowikowski.demo.crud.productGroup;

import org.springframework.data.jpa.domain.Specification;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;
import pl.slowikowski.demo.crud.abstraction.SearchOperation;

import java.util.ArrayList;
import java.util.List;

public class ProductGroupSearchSpecificationBuilder /*extends AbstractSearchSpecificationBuilder<ProductGroup>*/ {

    private List<SearchCriteria> params;

    public ProductGroupSearchSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public ProductGroupSearchSpecificationBuilder with(
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

    public Specification<ProductGroup> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification result = new ProductGroupSearchSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new ProductGroupSearchSpecification(params.get(i)))
                    : Specification.where(result).and(new ProductGroupSearchSpecification(params.get(i)));
        }

        return result;
    }
}



//FIXME sprobowac tworzyc cale Specification naraz, a nie oddzielnie jak wyzej
// FIXME w AbstractSearchSpecification dodac w CASE'ach ENUMy zamiast stringow
// FIXME zobaczyc, czy w ogole ProductSearchSpecification jest mi potrzebny, bo moze jest niepotrzebną warstwą pośrednią
// FIXME spytac sie co zrobic z auditem w przypadku testów, gdzie wstaje Spring (tymczasowo gdy Authentication == null, daje ID=0)
