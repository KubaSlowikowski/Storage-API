package pl.slowikowski.demo.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public abstract class AbstractController<T extends CommonService<E>, E extends AbstractDto>  {

    private final T service;

    public AbstractController(T service) {
        this.service = service;
    }

    @GetMapping
    Page<E> findAll(Pageable page) {
        return service.getAll(page);
    }

    @GetMapping(path = "/{id}")
    E findAllById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping
    E save(@RequestBody E dto) {
        return service.save(dto);
    }

    @PutMapping(path = "/{id}")
    E update(@PathVariable("id") int id, @RequestBody E dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    E delete(@PathVariable("id") int id) {
        return service.delete(id);
    }
}
