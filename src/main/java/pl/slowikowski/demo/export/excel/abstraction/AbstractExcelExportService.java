package pl.slowikowski.demo.export.excel.abstraction;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;

public abstract class AbstractExcelExportService {
    public abstract ResponseEntity<InputStreamResource> exportAllToExcel(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search);

    public abstract ResponseEntity<InputStreamResource> exportToExcel(@PathVariable("id") Long id);

    protected ResponseEntity<InputStreamResource> createResponse(ByteArrayInputStream bais, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + fileName + ".xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(bais));
    }
}
