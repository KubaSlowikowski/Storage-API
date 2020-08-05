package pl.slowikowski.demo.crud.productGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.crud.abstraction.AbstractController;

@RestController
@RequestMapping("/api/groups")
public class ProductGroupController extends AbstractController<ProductGroupService, ProductGroupDTO> {
    @Autowired
    ProductGroupRepository repo;
    @Autowired
    ProductGroupMapper mapper;

    public ProductGroupController(final ProductGroupServiceImpl service) {
        super(service);
    }
}
