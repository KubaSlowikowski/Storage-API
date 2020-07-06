package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.logic.ProductGroupService;
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
    private ProductGroupService service;

    public ProductGroupController(final ProductGroupRepository repository, final ProductGroupService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<ProductGroup>> findAllGroups() {
        logger.warn("Exposing all the product groups!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    ResponseEntity<List<ProductGroup>> findAllGroups(Pageable page) {
        logger.warn("Exposing all the product groups!");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PostMapping()
    ResponseEntity<ProductGroup> createGroup(@RequestBody @Valid ProductGroup toCreate) {
        ProductGroup result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody @Valid ProductGroup toUpdate) {
        try {
            service.updateGroup(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}