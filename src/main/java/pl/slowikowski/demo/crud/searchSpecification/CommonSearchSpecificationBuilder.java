package pl.slowikowski.demo.crud.searchSpecification;

import org.springframework.data.jpa.domain.Specification;
import pl.slowikowski.demo.crud.abstraction.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

public class CommonSearchSpecificationBuilder<E extends AbstractEntity> {
    private List<SearchCriteria> params;

    public CommonSearchSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final CommonSearchSpecificationBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public CommonSearchSpecificationBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {

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

    public Specification<E> build() {
        if (params.size() == 0) {
            return null;
        }
        Specification<E> result = new CommonSearchSpecification<E>(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new CommonSearchSpecification<E>(params.get(i)))
                    : Specification.where(result).and(new CommonSearchSpecification<E>(params.get(i)));
        }
        return result;
    }

    public final CommonSearchSpecificationBuilder with(CommonSearchSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final CommonSearchSpecificationBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }

}
