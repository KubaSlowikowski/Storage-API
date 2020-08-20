package pl.slowikowski.demo.export.excel.abstraction;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "${storage.front.url}", allowCredentials = "true")
public abstract class AbstractExcelExportController<T extends AbstractExcelExportService> {

    private final T service;

    public AbstractExcelExportController(final T service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> exportAllToPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        return service.exportToExcel(pageable, search);
    }
}
