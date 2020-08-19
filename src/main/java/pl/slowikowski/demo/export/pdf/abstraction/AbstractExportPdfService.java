package pl.slowikowski.demo.export.pdf.abstraction;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;

public abstract class AbstractExportPdfService {

    public abstract ResponseEntity<InputStreamResource> exportToPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search);

    protected ResponseEntity<InputStreamResource> createResponse(ByteArrayInputStream bais, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + fileName + ".pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));
    }
}
