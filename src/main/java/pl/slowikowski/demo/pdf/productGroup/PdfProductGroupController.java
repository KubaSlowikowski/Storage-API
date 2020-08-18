package pl.slowikowski.demo.pdf.productGroup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.pdf.abstraction.AbstractExportController;

@RestController
@RequestMapping("/pdf/groups")
public class PdfProductGroupController extends AbstractExportController<ProductGroupExportService> {
    public PdfProductGroupController(final ProductGroupExportService exportService) {
        super(exportService);
    }
}
