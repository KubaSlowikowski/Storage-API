package pl.slowikowski.demo.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('MODERATOR')")
public interface CommonService<D> {
    Page<D> getAll(Specification specs, Pageable page);

    D findById(Long id);

    D save(D dto);

    D update(Long id, D dto);

    D delete(Long id);
}
