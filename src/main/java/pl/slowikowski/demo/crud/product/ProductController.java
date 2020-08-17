package pl.slowikowski.demo.crud.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.crud.abstraction.AbstractController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends AbstractController<ProductService, ProductDTO> {

    private final ProductService service;

    public ProductController(final ProductService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(path = "/all/{id}")
    Page<ProductDTO> findAllProductsByGroupId(@PathVariable("id") Long groupId, @PageableDefault Pageable page) {
        return service.findAllByGroupId(groupId, page);
    }

    @PostMapping(path = "/{id}")
    ProductDTO buyProduct(@PathVariable Long id) {
        return service.buyProduct(id);
    }
}
