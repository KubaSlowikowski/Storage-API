package pl.slowikowski.demo.crud.product;

import pl.slowikowski.demo.crud.abstraction.CommonService;

import java.util.List;

public interface ProductService extends CommonService<ProductDTO> {

    List<ProductDTO> findAllByGroupId(Long groupId);

    ProductDTO buyProduct(Long id);
}
