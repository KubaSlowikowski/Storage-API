package pl.slowikowski.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.model.ProductGroupDTO;
import pl.slowikowski.demo.service.ProductGroupService;
import pl.slowikowski.demo.service.impl.ProductGroupServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductGroupController {
    private static final Logger logger = LoggerFactory.getLogger(ProductGroupController.class);
    private ProductGroupService service;

    public ProductGroupController(final ProductGroupServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    List<ProductGroupDTO> findAllGroups(Pageable page) {
        logger.warn("Exposing all the product groups!");
        return service.findAllProductGroups(page);
    }

    @GetMapping(path = "/{id}")
    ProductGroupDTO findGroupById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping()
    ProductGroupDTO createGroup(@RequestBody ProductGroupDTO toCreate) {
        return service.saveProductGroup(toCreate);
    }

    @PutMapping(path = "/{id}")
    ProductGroupDTO updateGroup(@PathVariable("id") int id, @RequestBody @Valid ProductGroupDTO toUpdate) {
        return service.updateGroup(id, toUpdate);
    }

    @DeleteMapping(path = "/{id}")
    ProductGroupDTO deleteGroup(@PathVariable("id") int id) {
        return service.deleteProductById(id);
    }
}
