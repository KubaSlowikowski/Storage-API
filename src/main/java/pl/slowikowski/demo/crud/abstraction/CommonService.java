package pl.slowikowski.demo.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('MODERATOR')")
public interface CommonService<D> {
    Page<D> getAll(Pageable page, String search);

    D findById(Long id);

    D save(D dto);

    D update(Long id, D dto);

    D delete(Long id);
}
