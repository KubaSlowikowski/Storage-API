package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.logic.ProductService;
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
    private final ProductService service;

    public ProductController(final ProductRepository repository, final ProductService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Product>> findAllProducts() {
        logger.warn("Exposing all the products!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    ResponseEntity<List<Product>> findAllProducts(Pageable page) {
        logger.warn("Exposing all the products!");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody @Valid Product toCreate) {
        Product result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody @Valid Product toUpdate) {
        try {
            service.updateProduct(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buyProduct(@PathVariable int id) {
        try {
            service.buyProduct(id);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}