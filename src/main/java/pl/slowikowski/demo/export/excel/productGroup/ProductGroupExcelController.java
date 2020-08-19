package pl.slowikowski.demo.export.excel.productGroup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportController;

@RestController
@RequestMapping("${export.excel.url}/groups")
public class ProductGroupExcelController extends AbstractExcelExportController<ProductGroupExcelExportService> {
    public ProductGroupExcelController(final ProductGroupExcelExportService exportService) {
        super(exportService);
    }
}
