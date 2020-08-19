package pl.slowikowski.demo.export.excel.book;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportService;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.soap_client.book.BookSoapClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class BookExcelExportService extends AbstractExcelExportService {

    private final BookSoapClient client;
    private final BookExcelService excelService;

    public BookExcelExportService(final BookSoapClient client, final BookExcelService excelService) {
        this.client = client;
        this.excelService = excelService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToExcel(Pageable pageable, String search) {
        List<BookDTO> books = client.findAll(pageable, search).getContent();
        ByteArrayInputStream bais = excelService.exportToExcel(books, "Books_list");
        return createResponse(bais, "books");
    }
}
