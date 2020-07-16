package pl.slowikowski.demo.product;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductController {
    private final ProductService service;

    public ProductController(final ProductServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    List<ProductDTO> findAllProducts(Pageable page) {
        return service.findAllProducts(page);
    }

    @GetMapping(path = "/{id}")
    ProductDTO findProductById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @GetMapping(path = "/all/{id}")
    List<ProductDTO> findAllProductsByGroupId(@PathVariable("id") int groupId) {
        return service.findAllByGroupId(groupId);
    }

    @PostMapping
    ProductDTO createProduct(@RequestBody ProductDTO toCreate) {
        return service.saveProduct(toCreate);
    }

    @PutMapping(path = "/{id}")
    ProductDTO updateProduct(@PathVariable("id") int id, @RequestBody @Valid ProductDTO toUpdate) {
        return service.updateProduct(id, toUpdate);
    }

    @DeleteMapping(path = "/{id}")
    ProductDTO deleteProduct(@PathVariable("id") int id) {
        return service.deleteProductById(id);
    }

    @PostMapping(path = "/{id}")
    ProductDTO buyProduct(@PathVariable int id) {
        return service.buyProduct(id);
    }
}
