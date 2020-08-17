package pl.slowikowski.demo.crud.productGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.crud.abstraction.CommonRepository;

import java.util.Optional;

@Repository
public interface ProductGroupRepository extends CommonRepository<ProductGroup> {

    @Override //solves hibernate LazyInitializationException problem
    @Query("select distinct g from ProductGroup g left join fetch g.products where g.id=:id")
    Optional<ProductGroup> findById(Long id);

    @Override
    @Query(
            value = "select distinct g from ProductGroup g left join fetch g.products",
            countQuery = "select count(g) from ProductGroup g left join g.products")
    Page<ProductGroup> findAll(Specification<ProductGroup> specification, Pageable pageable);
}

