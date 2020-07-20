package pl.slowikowski.demo.product;

import pl.slowikowski.demo.abstraction.CommonService;

import java.util.List;

public interface ProductService extends CommonService<ProductDTO> {

    List<ProductDTO> findAllByGroupId(int groupId);

    ProductDTO buyProduct(int id);
}
