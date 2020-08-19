package pl.slowikowski.demo.export.excel.reader;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportService;
import pl.slowikowski.demo.export.excel.abstraction.CommonExcelService;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap_client.reader.ReaderSoapClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ReaderExcelExportService extends AbstractExcelExportService {

    private final ReaderSoapClient client;
    private final CommonExcelService<ReaderDTO> excelService;

    public ReaderExcelExportService(final ReaderSoapClient client, final CommonExcelService<ReaderDTO> excelService) {
        this.client = client;
        this.excelService = excelService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToExcel(Pageable pageable, String search) {
        List<ReaderDTO> books = client.findAll(pageable, search).getContent();
        ByteArrayInputStream bais = excelService.exportToExcel(books, "Readers_list", List.of("password"));
        return createResponse(bais, "readers");
    }
}
