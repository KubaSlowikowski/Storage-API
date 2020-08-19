package pl.slowikowski.demo.export.pdf.reader;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractExportPdfService;
import pl.slowikowski.demo.export.pdf.abstraction.CommonPdfService;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap_client.reader.ReaderSoapClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ReaderPdfExportService extends AbstractExportPdfService {
    private final ReaderSoapClient client;
    private final CommonPdfService<ReaderDTO> pdfService;

    public ReaderPdfExportService(final ReaderSoapClient client, final CommonPdfService<ReaderDTO> pdfService) {
        this.client = client;
        this.pdfService = pdfService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToPdf(Pageable pageable, String search) {
        List<ReaderDTO> readers = client.findAll(pageable, search).getContent();
        ByteArrayInputStream bais = pdfService.exportToPdf(readers, "Readers list", List.of("password"));
        return createResponse(bais, "readers");
    }
}
