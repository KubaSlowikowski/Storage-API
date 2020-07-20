package pl.slowikowski.demo.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<D> {
    Page<D> getAll(Pageable page);

    D findById(int id);

    D save(D dto);

    D update(int id, D dto);

    D delete(int id);
}
