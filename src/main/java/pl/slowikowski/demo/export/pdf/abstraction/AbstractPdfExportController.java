package pl.slowikowski.demo.export.pdf.abstraction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.slowikowski.demo.export.ExportDto;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public abstract class AbstractPdfExportController<T extends AbstractExportPdfService> {

    private final T service;

    public AbstractPdfExportController(T service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<byte[]> exportToPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        ExportDto pdfReport = service.exportToPdf(pageable, search);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + pdfReport.getFileName() + "_" + LocalDate.now() + pdfReport.getExtension())
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport.getByteArray());
    }
}
