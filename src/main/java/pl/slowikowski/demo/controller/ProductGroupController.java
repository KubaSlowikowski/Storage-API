package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.model.Product;
import pl.slowikowski.demo.model.ProductGroup;
import pl.slowikowski.demo.model.ProductGroupRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class ProductGroupController {
    private static final Logger logger = LoggerFactory.getLogger(ProductGroupController.class);
    private ProductGroupRepository repository;

    public ProductGroupController(final ProductGroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    ResponseEntity<List<ProductGroup>> findAllGroups() {
        logger.warn("Exposing all the product groups!");
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping()
    ResponseEntity<ProductGroup> createGroup (@RequestBody @Valid ProductGroup toCreate) {
        ProductGroup result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody @Valid ProductGroup toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent( group -> {
                            group.updateFrom(toUpdate);
                            repository.save(group);
                        }
                );
        return ResponseEntity.noContent().build();
    }
}