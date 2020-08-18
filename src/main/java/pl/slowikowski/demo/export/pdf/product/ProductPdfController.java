package pl.slowikowski.demo.export.pdf.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractPdfExportController;

@RestController
@RequestMapping("/export/pdf/products")
public class ProductPdfController extends AbstractPdfExportController<ProductPdfExportService> {
    public ProductPdfController(final ProductPdfExportService exportService) {
        super(exportService);
    }
}
