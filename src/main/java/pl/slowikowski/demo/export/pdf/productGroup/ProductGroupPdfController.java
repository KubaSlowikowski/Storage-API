package pl.slowikowski.demo.export.pdf.productGroup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractPdfExportController;

@RestController
@RequestMapping("/export/pdf/groups")
public class ProductGroupPdfController extends AbstractPdfExportController<ProductGroupPdfExportService> {
    public ProductGroupPdfController(final ProductGroupPdfExportService exportService) {
        super(exportService);
    }
}
