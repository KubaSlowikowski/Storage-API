package pl.slowikowski.demo.export.pdf.reader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractPdfExportController;

@RestController
@RequestMapping("${export.pdf.url}/readers")
public class ReaderPdfController extends AbstractPdfExportController<ReaderPdfExportService> {
    public ReaderPdfController(final ReaderPdfExportService exportService) {
        super(exportService);
    }
}
