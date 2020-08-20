package pl.slowikowski.demo.export.pdf.book;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.export.ExportDto;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractExportPdfService;
import pl.slowikowski.demo.export.pdf.abstraction.CommonPdfService;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.soap_client.book.BookSoapClient;

import java.util.List;

@Service
public class BookPdfExportService extends AbstractExportPdfService {
    private final BookSoapClient client;
    private final CommonPdfService<BookDTO> pdfService;

    public BookPdfExportService(final BookSoapClient client, final CommonPdfService<BookDTO> pdfService) {
        this.client = client;
        this.pdfService = pdfService;
    }

    @Override
    public ExportDto exportToPdf(Pageable pageable, String search) {
        List<BookDTO> books = client.findAll(pageable, search).getContent();
        return pdfService.exportToPdf(books, "Books list", "books", ".pdf");
    }
}
