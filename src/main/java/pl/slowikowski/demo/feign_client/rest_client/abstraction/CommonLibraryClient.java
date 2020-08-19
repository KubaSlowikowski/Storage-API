package pl.slowikowski.demo.feign_client.rest_client.abstraction;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.feign_client.CustomPageImpl;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;

public interface CommonLibraryClient<D extends AbstractLibraryDTO> {
    @GetMapping
    CustomPageImpl<D> findAll(Pageable page, @RequestParam(value = "search", required = false) String search);

    @PostMapping
    D create(@RequestBody D dto);

    @GetMapping(path = "/{id}")
    D getById(@PathVariable("id") Long id);

    @PutMapping(path = "/{id}")
    D update(@RequestBody D dto, @PathVariable("id") Long id);

    @DeleteMapping(path = "/{id}")
    D delete(@PathVariable("id") Long id);
}
