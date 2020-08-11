package pl.slowikowski.demo.feign_client.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface CommonLibraryClient<D extends AbstractLibraryDTO> {
    @GetMapping
    Page<D> findAll(Pageable page, @RequestParam(value = "search", required = false) String search, @RequestHeader("Authorization") String authorization);

    @PostMapping
    D create(@RequestBody D dto, @RequestHeader("Authorization") String authorization);

    @GetMapping(path = "/{id}")
    D getById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization);

    @PutMapping(path = "/{id}")
    D update(@RequestBody D dto, @PathVariable("id") Long id, @RequestHeader("Authorization") String authorization);

    @DeleteMapping(path = "/{id}")
    D delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization);
}
