package pl.slowikowski.demo.export.excel.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportController;

@RestController
@RequestMapping("${export.excel.url}/products")
public class ProductExcelController extends AbstractExcelExportController<ProductExcelExportService> {
    public ProductExcelController(final ProductExcelExportService exportService) {
        super(exportService);
    }
}
