package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.service.ProductGroupRepositoryImpl;
import pl.slowikowski.demo.persistence.model.ProductGroup;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class ProductGroupController {
    private static final Logger logger = LoggerFactory.getLogger(ProductGroupController.class);
    private ProductGroupRepositoryImpl service;

    public ProductGroupController(final ProductGroupRepositoryImpl service) {
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<ProductGroup>> findAllGroups() {
        logger.warn("Exposing all the product groups!");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    ResponseEntity<List<ProductGroup>> findAllGroups(Pageable page) {
        logger.warn("Exposing all the product groups!");
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ProductGroup> findGroupById(@PathVariable("id") int id) {
        try {
            var result = service.findById(id);
            return ResponseEntity.ok(result);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }

    @PostMapping()
    ResponseEntity<ProductGroup> createGroup(@RequestBody @Valid ProductGroup toCreate) {
        ProductGroup result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping(path = "/{id}")
    ResponseEntity<?> updateGroup(@PathVariable("id") int id, @RequestBody @Valid ProductGroup toUpdate) {
        try {
            service.updateGroup(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}