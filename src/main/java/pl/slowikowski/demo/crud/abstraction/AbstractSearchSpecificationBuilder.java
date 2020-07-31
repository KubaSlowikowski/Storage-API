//package pl.slowikowski.demo.crud.abstraction;
//
//import org.springframework.data.jpa.domain.Specification;
//import pl.slowikowski.demo.crud.product.Product;
//import pl.slowikowski.demo.crud.product.ProductSearchSpecification;
//import pl.slowikowski.demo.crud.product.ProductSearchSpecificationBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public abstract class AbstractSearchSpecificationBuilder<E extends AbstractEntity> {
//    private final List<SearchCriteria> params;
//
//    public AbstractSearchSpecificationBuilder() {
//        params = new ArrayList<SearchCriteria>();
//    }
//
//    protected AbstractSearchSpecificationBuilder(List<SearchCriteria> params) {
//        this.params = params;
//    }
//
//    public ProductSearchSpecificationBuilder with(String key, String operation, Object value) {
//        params.add(new SearchCriteria(key, operation, value));
//        return this;
//    }
//
//    public Specification<Product> build() {
//        if (params.size() == 0) {
//            return null;
//        }
//
//        List<Specification> specs = params.stream()
//                .map(ProductSearchSpecification::new)
//                .collect(Collectors.toList());
//
//        Specification result = specs.get(0);
//
//        for (int i = 1; i < params.size(); i++) {
//            result = params.get(i)
//                    .isOrPredicate()
//                    ? Specification.where(result)
//                    .or(specs.get(i))
//                    : Specification.where(result)
//                    .and(specs.get(i));
//        }
//        return result;
//    }
//}
