package pl.slowikowski.demo.export.pdf.abstraction;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public abstract class AbstractPdfExportController<T extends AbstractExportPdfService> {

    private final T service;

    public AbstractPdfExportController(T service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> exportAllToPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        return service.exportAllToPdf(pageable, search);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InputStreamResource> exportToPdf(@PathVariable("id") Long id) {
        return service.exportToPdf(id);
    }
}
