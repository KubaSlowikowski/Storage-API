package pl.slowikowski.demo.productGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductGroupService {

    Page<ProductGroupDTO> findAllProductGroups(Pageable page);

    ProductGroupDTO findById(int id);

    ProductGroupDTO saveProductGroup(ProductGroupDTO toCreate);

    ProductGroupDTO updateGroup(int id, ProductGroupDTO toUpdate);

    ProductGroupDTO deleteProductById(int id);
}
