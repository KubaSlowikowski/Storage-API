package pl.slowikowski.demo.pdf.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.pdf.abstraction.AbstractExportController;

@RestController
@RequestMapping("/pdf/products")
public class PdfProductController extends AbstractExportController<ProductExportService> {
    public PdfProductController(final ProductExportService exportService) {
        super(exportService);
    }
}
