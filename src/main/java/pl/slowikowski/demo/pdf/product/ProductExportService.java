package pl.slowikowski.demo.pdf.product;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.demo.pdf.abstraction.AbstractExportService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ProductExportService extends AbstractExportService {
    private final ProductService service;
    private final ProductPdfService pdfService;

    public ProductExportService(final ProductService service, final ProductPdfService exportService) {
        this.service = service;
        this.pdfService = exportService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportAllToPdf(Pageable pageable, String search) {
        List<ProductDTO> products = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = pdfService.exportToPdf(products, "Products list", 6);
        return createResponse(bais, "products");
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToPdf(Long id) {
        List<ProductDTO> product = List.of(service.findById(id));
        ByteArrayInputStream bais = pdfService.exportToPdf(product, "Product " + product.get(0).getId(), 6);
        return createResponse(bais, "product" + product.get(0).getId());
    }

}
