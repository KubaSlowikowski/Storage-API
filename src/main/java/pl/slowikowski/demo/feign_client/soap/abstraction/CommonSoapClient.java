package pl.slowikowski.demo.feign_client.soap.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.feign_client.crud.abstraction.AbstractLibraryDTO;


public interface CommonSoapClient<D extends AbstractLibraryDTO> {
    Page<D> findAll(Pageable pageable, String search);

    D getById(long id);

    D add(D dto);

    D update(D dto, long id);

    D delete(long id);
}
