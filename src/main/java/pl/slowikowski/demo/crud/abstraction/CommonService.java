package pl.slowikowski.demo.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CommonService<D> {
    Page<D> getAll(Specification specs, Pageable page);

    D findById(int id);

    D save(D dto);

    D update(int id, D dto);

    D delete(int id);
}
