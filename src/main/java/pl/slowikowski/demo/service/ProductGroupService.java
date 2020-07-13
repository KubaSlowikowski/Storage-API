package pl.slowikowski.demo.service;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.model.ProductGroupDTO;

import java.util.List;

public interface ProductGroupService {

    List<ProductGroupDTO> findAllProductGroups(Pageable page);

    ProductGroupDTO findById(int id);

    ProductGroupDTO saveProductGroup(ProductGroupDTO toCreate);

    ProductGroupDTO updateGroup(int id, ProductGroupDTO toUpdate);

    ProductGroupDTO deleteProductById(int id);
}
