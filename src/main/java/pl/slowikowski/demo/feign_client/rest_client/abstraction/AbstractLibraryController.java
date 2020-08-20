package pl.slowikowski.demo.feign_client.rest_client.abstraction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.export.ExportDto;
import pl.slowikowski.demo.feign_client.CustomPageImpl;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;

import java.time.LocalDate;

public class AbstractLibraryController<D extends AbstractLibraryDTO, T extends CommonLibraryClient<D>> {
    private final T client;
    private final AbstractLibraryClientService<D,T> service;

    public AbstractLibraryController(final T client, final AbstractLibraryClientService<D,T> service) {
        this.client = client;
        this.service = service;
    }

    @GetMapping
    CustomPageImpl<D> findAll(Pageable page, @RequestParam(value = "search", required = false) String search) {
        return client.findAll(page, search);
    }

    @PostMapping
    D create(@RequestBody D dto) {
        return client.create(dto);
    }

    @GetMapping(path = "/{id}")
    D getById(@PathVariable("id") Long id) {
        return client.getById(id);
    }

    @PutMapping(path = "/{id}")
    D update(@RequestBody D dto, @PathVariable("id") Long id) {
        return client.update(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    D delete(@PathVariable("id") Long id) {
        return client.delete(id);
    }

    @GetMapping(path = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        ExportDto pdfReport = service.getPdfReport(pageable, search);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + pdfReport.getFileName() + "_" + LocalDate.now() + pdfReport.getExtension())
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport.getByteArray());
    }
}
