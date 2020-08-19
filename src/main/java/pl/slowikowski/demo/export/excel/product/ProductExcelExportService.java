package pl.slowikowski.demo.export.excel.product;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ProductExcelExportService extends AbstractExcelExportService {
    private final ProductService service;
    private final ProductExcelService excelService;

    public ProductExcelExportService(final ProductService service, final ProductExcelService excelService) {
        this.service = service;
        this.excelService = excelService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToExcel(Pageable pageable, String search) {
        List<ProductDTO> products = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = excelService.exportToExcel(products, "Products_list");
        return createResponse(bais, "products");
    }

}
