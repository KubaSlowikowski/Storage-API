package pl.slowikowski.demo.pdf;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    private final ProductService service;
    private final ProductExportService exportService;

    public PdfController(final ProductService service, final ProductExportService exportService) {
        this.service = service;
        this.exportService = exportService;
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> exportProductToPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        List<ProductDTO> products = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = exportService.productsPdfReport(products);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));
    }
}
