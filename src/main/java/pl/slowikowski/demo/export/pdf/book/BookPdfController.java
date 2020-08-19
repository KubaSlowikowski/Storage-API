package pl.slowikowski.demo.export.pdf.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractPdfExportController;

@RestController
@RequestMapping("${export.pdf.url}/books")
public class BookPdfController extends AbstractPdfExportController<BookPdfExportService> {
    public BookPdfController(final BookPdfExportService exportService) {
        super(exportService);
    }
}
