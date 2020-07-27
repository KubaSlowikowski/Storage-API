package pl.slowikowski.demo.product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.slowikowski.demo.abstraction.CommonRepository;

import java.util.List;

@Repository
public interface ProductRepository extends CommonRepository<Product> {

    List<Product> findAllByGroup_Id(Integer groupId);

    @Modifying
    @Query(value = "UPDATE public.products SET product_group_id=1 where product_group_id=:id", nativeQuery = true)
    void assignProductsFromGroupWithIdToSystemGroup(@Param("id") int id);
}
