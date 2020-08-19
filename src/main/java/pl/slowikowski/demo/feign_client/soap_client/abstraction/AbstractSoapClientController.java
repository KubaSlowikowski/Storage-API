package pl.slowikowski.demo.feign_client.soap_client.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;

public class AbstractSoapClientController<D extends AbstractLibraryDTO, T extends CommonSoapClient<D>> {

    private final T client;

    public AbstractSoapClientController(T client) {
        this.client = client;
    }

    @GetMapping
    public Page<D> findAll(Pageable page, @RequestParam(value = "search", required = false) String search) {
        return client.findAll(page, search);
    }

    @GetMapping("/{id}")
    public D getById(@PathVariable long id) {
        return client.getById(id);
    }

    @PostMapping
    public D save(@RequestBody D dto) {
        return client.save(dto);
    }

    @PutMapping("/{id}")
    public D update(@RequestBody D dto, @PathVariable long id) {
        return client.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public D delete(@PathVariable long id) {
        return client.delete(id);
    }
}
