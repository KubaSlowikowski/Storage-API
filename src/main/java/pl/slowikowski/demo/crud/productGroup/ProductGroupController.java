package pl.slowikowski.demo.crud.productGroup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.crud.abstraction.AbstractController;

@RestController
@RequestMapping("/groups")
public class ProductGroupController extends AbstractController<ProductGroupService, ProductGroupDTO> {
    public ProductGroupController(final ProductGroupServiceImpl service) {
        super(service);
    }
}
