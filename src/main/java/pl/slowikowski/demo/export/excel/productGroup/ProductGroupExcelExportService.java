package pl.slowikowski.demo.export.excel.productGroup;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.demo.export.excel.abstraction.AbstractExcelExportService;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ProductGroupExcelExportService extends AbstractExcelExportService {
    private final ProductGroupService service;
    private final ProductGroupExcelService excelService;

    public ProductGroupExcelExportService(final ProductGroupService service, final ProductGroupExcelService excelService) {
        this.service = service;
        this.excelService = excelService;
    }

    @Override
    public ResponseEntity<InputStreamResource> exportAllToExcel(Pageable pageable, String search) {
        List<ProductGroupDTO> products = service.getAll(pageable, search).getContent();
        ByteArrayInputStream bais = excelService.exportToExcel(products, "Product_groups_list");
        return createResponse(bais, "productGroups");
    }

    @Override
    public ResponseEntity<InputStreamResource> exportToExcel(Long id) {
        List<ProductGroupDTO> product = List.of(service.findById(id));
        ByteArrayInputStream bais = excelService.exportToExcel(product, "Product_Group_" + product.get(0).getId());
        return createResponse(bais, "productGroup" + product.get(0).getId());
    }
}
