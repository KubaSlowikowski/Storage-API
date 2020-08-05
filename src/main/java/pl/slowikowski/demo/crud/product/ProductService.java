package pl.slowikowski.demo.crud.product;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.slowikowski.demo.crud.abstraction.CommonService;

import java.util.List;

public interface ProductService extends CommonService<ProductDTO> {

    @PreAuthorize("hasRole('USER')")
    List<ProductDTO> findAllByGroupId(Long groupId);

    @PreAuthorize("hasRole('USER')")
    ProductDTO buyProduct(Long id);
}
