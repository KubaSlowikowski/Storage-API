package pl.slowikowski.demo.pdf.productGroup;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.demo.pdf.abstraction.AbstractExportService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ProductGroupExportService extends AbstractExportService {
    private final ProductGroupService service;
    private final ProductGroupPdfService pdfService;

    public ProductGroupExportService(final ProductGroupService service, final ProductGroupPdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportAllToPdf(Pageable pageable, String search) {
        List<ProductGroupDTO> products = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = pdfService.exportToPdf(products, "Product groups list", 4);
        return createResponse(bais, "productsGroups");
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToPdf(Long id) {
        List<ProductGroupDTO> product = List.of(service.findById(id));
        ByteArrayInputStream bais = pdfService.exportToPdf(product, "Product Group " + product.get(0).getId(), 4);
        return createResponse(bais, "productGroup" + product.get(0).getId());
    }
}
