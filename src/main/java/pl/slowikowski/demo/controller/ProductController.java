package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.model.ProductDTO;
import pl.slowikowski.demo.service.ProductService;
import pl.slowikowski.demo.service.impl.ProductServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    public ProductController(final ProductServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    List<ProductDTO> findAllProducts(Pageable page) {
        logger.warn("Exposing all the products!");
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
