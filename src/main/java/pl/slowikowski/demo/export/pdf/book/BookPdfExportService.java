package pl.slowikowski.demo.export.pdf.book;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractExportPdfService;
import pl.slowikowski.demo.export.pdf.abstraction.CommonPdfService;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.soap_client.book.BookSoapClient;

import java.io.ByteArrayInputStream;
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
    public ResponseEntity<InputStreamResource> exportToPdf(Pageable pageable, String search) {
        List<BookDTO> products = client.findAll(pageable, search).getContent();
        ByteArrayInputStream bais = pdfService.exportToPdf(products, "Books list");
        return createResponse(bais, "books");
    }
}
