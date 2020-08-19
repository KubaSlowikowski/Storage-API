package pl.slowikowski.demo.export.pdf.product;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractExportPdfService;
import pl.slowikowski.demo.export.pdf.abstraction.CommonPdfService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ProductPdfExportService extends AbstractExportPdfService {
    private final ProductService service;
    private final CommonPdfService<ProductDTO> pdfService;

    public ProductPdfExportService(final ProductService service, final CommonPdfService<ProductDTO> exportService) {
        this.service = service;
        this.pdfService = exportService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToPdf(Pageable pageable, String search) {
        List<ProductDTO> products = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = pdfService.exportToPdf(products, "Products list");
        return createResponse(bais, "products");
    }
}
