package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.model.ProductDTO;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.service.ProductRepositoryImpl;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepositoryImpl service;

    public ProductController(final ProductRepositoryImpl service) {
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Product>> findAllProducts() {
        logger.warn("Exposing all the products!");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    ResponseEntity<List<Product>> findAllProducts(Pageable page) {
        logger.warn("Exposing all the products!");
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ProductDTO> findProductById(@PathVariable("id") int id) {
        try {
            var result = service.findById(id);
            return ResponseEntity.ok(result);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }

    @GetMapping(path = "/all/{id}")
    ResponseEntity<List<Product>> findAllProductsByGroupId(@PathVariable("id") int groupId) {
        try {
            var result = service.findAllByGroupId(groupId);
            return ResponseEntity.ok(result);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody @Valid Product toCreate) {
        Product result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping(path = "/{id}")
    ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody @Valid Product toUpdate) {
        try {
            service.updateProduct(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<?> buyProduct(@PathVariable int id) {
        try {
            service.buyProduct(id);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}