package pl.slowikowski.demo.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductDTO> findAllProducts(Pageable page);

    ProductDTO findById(int id);

    List<ProductDTO> findAllByGroupId(int groupId);

    ProductDTO saveProduct(ProductDTO toCreate);

    ProductDTO updateProduct(int id, ProductDTO toUpdate);

    ProductDTO deleteProductById(int id);

    ProductDTO buyProduct(int id);
}
