package pl.slowikowski.demo.feign_client.rest_client.abstraction;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.feign_client.CustomPageImpl;

public class AbstractLibraryController<D extends AbstractLibraryDTO, T extends CommonLibraryClient<D>> {
    private final T client;

    public AbstractLibraryController(final T client) {
        this.client = client;
    }

    @GetMapping
    CustomPageImpl<D> findAll(Pageable page, @RequestParam(value = "search", required = false) String search) {
        return client.findAll(page, search);
    }

    @PostMapping
    D create(@RequestBody D dto) {
        return client.create(dto);
    }

    @GetMapping(path = "/{id}")
    D getById(@PathVariable("id") Long id) {
        return client.getById(id);
    }

    @PutMapping(path = "/{id}")
    D update(@RequestBody D dto, @PathVariable("id") Long id) {
        return client.update(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    D delete(@PathVariable("id") Long id) {
        return client.delete(id);
    }
}
