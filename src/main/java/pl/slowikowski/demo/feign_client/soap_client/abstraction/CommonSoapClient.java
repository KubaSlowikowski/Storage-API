package pl.slowikowski.demo.feign_client.soap_client.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;


public interface CommonSoapClient<D extends AbstractLibraryDTO> {
    Page<D> findAll(Pageable pageable, String search);

    D getById(long id);

    D save(D dto);

    D update(D dto, long id);

    D delete(long id);
}
