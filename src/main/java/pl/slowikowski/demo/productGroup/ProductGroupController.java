package pl.slowikowski.demo.productGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductGroupController {
    private ProductGroupService service;

    public ProductGroupController(final ProductGroupServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    Page<ProductGroupDTO> findAllGroups(Pageable page) {
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
