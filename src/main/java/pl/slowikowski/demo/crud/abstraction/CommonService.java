package pl.slowikowski.demo.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CommonService<D> {
    @PreAuthorize("hasRole('USER')")
    Page<D> getAll(Pageable page, String search);

    @PreAuthorize("hasRole('USER')")
    D findById(Long id);

    @PreAuthorize("hasRole('USER')")
    D save(D dto);

    @PreAuthorize("hasRole('MODERATOR')")
    D update(Long id, D dto);

    @PreAuthorize("hasRole('ADMIN')")
    D delete(Long id);
}
