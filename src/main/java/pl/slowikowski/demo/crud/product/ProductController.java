package pl.slowikowski.demo.crud.product;

import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.crud.abstraction.AbstractController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductService, ProductDTO> {

    private final ProductService service;
    private final ProductMapper mapper;
    private final ProductRepository repo;

    public ProductController(final ProductService service, ProductMapper mapper, ProductRepository repo) {
        super(service);
        this.service = service;
        this.mapper = mapper;
        this.repo = repo;
    }

    @GetMapping(path = "/all/{id}")
    List<ProductDTO> findAllProductsByGroupId(@PathVariable("id") Long groupId) {
        return service.findAllByGroupId(groupId);
    }

    @PostMapping(path = "/{id}")
    ProductDTO buyProduct(@PathVariable Long id) {
        return service.buyProduct(id);
    }
}
