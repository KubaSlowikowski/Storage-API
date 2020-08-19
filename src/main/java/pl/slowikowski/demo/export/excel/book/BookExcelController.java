package pl.slowikowski.demo.export.excel.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportController;

@RestController
@RequestMapping("${export.excel.url}/books")
public class BookExcelController extends AbstractExcelExportController<BookExcelExportService> {
    public BookExcelController(final BookExcelExportService exportService) {
        super(exportService);
    }
}
