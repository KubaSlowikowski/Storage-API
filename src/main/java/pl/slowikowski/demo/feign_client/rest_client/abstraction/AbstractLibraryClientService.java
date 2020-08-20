package pl.slowikowski.demo.feign_client.rest_client.abstraction;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.export.ExportDto;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;

import java.util.List;

public abstract class AbstractLibraryClientService<D extends AbstractLibraryDTO, T extends CommonLibraryClient<D>> implements CommonLibraryClientService<D> {

    private final T client;

    protected AbstractLibraryClientService(final T client) {
        this.client = client;
    }

    public ExportDto getPdfReport(Pageable pageable, String search) {
        List<D> dtos = client.findAll(pageable, search).getContent();
        return toPdfReport(dtos, ".pdf");
    }
}
