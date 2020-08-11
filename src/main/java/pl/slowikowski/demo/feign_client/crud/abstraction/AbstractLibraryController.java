package pl.slowikowski.demo.feign_client.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public class AbstractLibraryController<D extends AbstractLibraryDTO, T extends CommonLibraryClient<D>> {
    private final T client;

    public AbstractLibraryController(final T client) {
        this.client = client;
    }

    @GetMapping
    Page<D> findAll(Pageable page, @RequestParam(value = "search", required = false) String search, @RequestHeader String libraryAuth) {
        return client.findAll(page, search, libraryAuth);
    }

    @PostMapping
    D create(@RequestBody D dto, @RequestHeader String libraryAuth) {
        return client.create(dto, libraryAuth);
    }

    @GetMapping(path = "/{id}")
    D getById(@PathVariable("id") Long id, @RequestHeader String libraryAuth) {
        return client.getById(id, libraryAuth);
    }

    @PutMapping(path = "/{id}")
    D update(@RequestBody D dto, @PathVariable("id") Long id, @RequestHeader String libraryAuth) {
        return client.update(dto, id, libraryAuth);
    }

    @DeleteMapping(path = "/{id}")
    D delete(@PathVariable("id") Long id, @RequestHeader String libraryAuth) {
        return client.delete(id, libraryAuth);
    }
}
