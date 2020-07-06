package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.model.Product;
import pl.slowikowski.demo.model.ProductRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository repository;

    public ProductController(final ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    ResponseEntity<List<Product>> findAllProducts() {
        logger.warn("Exposing all the products!");
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping()
    ResponseEntity<Product> createProduct(@RequestBody @Valid Product toCreate) {
        Product result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody @Valid Product toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent( product -> {
                    product.updateFrom(toUpdate);
                    repository.save(product);
                        }
                );
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> buyProduct(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(product ->  {
                    product.toogle();
                    repository.save(product);
                });
        return ResponseEntity.noContent().build();
    }
}