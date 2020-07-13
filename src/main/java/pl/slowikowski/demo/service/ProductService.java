package pl.slowikowski.demo.service;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProducts(Pageable page);

    ProductDTO findById(int id);

    List<ProductDTO> findAllByGroupId(int groupId);

    ProductDTO saveProduct(ProductDTO toCreate);

    ProductDTO updateProduct(int id, ProductDTO toUpdate);

    ProductDTO deleteProductById(int id);

    ProductDTO buyProduct(int id);
}
