package pl.slowikowski.demo.export.excel.reader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportController;

@RestController
@RequestMapping("${export.excel.url}/readers")
public class ReaderExcelController extends AbstractExcelExportController<ReaderExcelExportService> {
    public ReaderExcelController(final ReaderExcelExportService service) {
        super(service);
    }
}
