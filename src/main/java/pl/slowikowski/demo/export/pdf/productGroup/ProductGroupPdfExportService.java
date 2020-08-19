package pl.slowikowski.demo.export.pdf.productGroup;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.demo.export.pdf.abstraction.AbstractExportPdfService;
import pl.slowikowski.demo.export.pdf.abstraction.CommonPdfService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ProductGroupPdfExportService extends AbstractExportPdfService {
    private final ProductGroupService service;
    private final CommonPdfService<ProductGroupDTO> pdfService;

    public ProductGroupPdfExportService(final ProductGroupService service, final CommonPdfService<ProductGroupDTO> pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToPdf(Pageable pageable, String search) {
        List<ProductGroupDTO> groups = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = pdfService.exportToPdf(groups, "Product groups list", null);
        return createResponse(bais, "productsGroups");
    }
}