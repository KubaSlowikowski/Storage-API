package pl.slowikowski.demo.crud.product;

import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.crud.abstraction.AbstractController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductService, ProductDTO> {

    private final ProductService service;

    public ProductController(final ProductService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(path = "/all/{id}")
    List<ProductDTO> findAllProductsByGroupId(@PathVariable("id") int groupId) {
        return service.findAllByGroupId(groupId);
    }

    @PostMapping(path = "/{id}")
    ProductDTO buyProduct(@PathVariable int id) {
        return service.buyProduct(id);
    }
}
