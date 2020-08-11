package pl.slowikowski.demo.crud.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.slowikowski.demo.crud.abstraction.CommonService;

public interface ProductService extends CommonService<ProductDTO> {

    @PreAuthorize("hasRole('USER')")
    Page<ProductDTO> findAllByGroupId(Long groupId, Pageable pageable);

    @PreAuthorize("hasRole('USER')")
    ProductDTO buyProduct(Long id);
}
