package pl.slowikowski.demo.productGroup;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductGroupService {

    List<ProductGroupDTO> findAllProductGroups(Pageable page);

    ProductGroupDTO findById(int id);

    ProductGroupDTO saveProductGroup(ProductGroupDTO toCreate);

    ProductGroupDTO updateGroup(int id, ProductGroupDTO toUpdate);

    ProductGroupDTO deleteProductById(int id);
}
