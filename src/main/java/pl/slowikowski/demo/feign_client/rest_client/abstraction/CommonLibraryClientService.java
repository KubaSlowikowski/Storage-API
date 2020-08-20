package pl.slowikowski.demo.feign_client.rest_client.abstraction;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.crud.abstraction.Exportable;
import pl.slowikowski.demo.export.ExportDto;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;

public interface CommonLibraryClientService<D extends AbstractLibraryDTO> extends Exportable<D> {
    ExportDto getPdfReport(Pageable pageable, String search);
}
