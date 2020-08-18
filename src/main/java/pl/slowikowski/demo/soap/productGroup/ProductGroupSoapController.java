package pl.slowikowski.demo.soap.productGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product_group.*;

import javax.jws.WebService;

@Controller
@WebService(endpointInterface = "pl.slowikowski.jakub.soap_example.product_group.ProductGroupClient")
@RequiredArgsConstructor
public class ProductGroupSoapController implements ProductGroupClient {

    private final ProductGroupSoapService service;

    @Override
    public GetAllResponse getAllProductGroups(GetAllProductGroupsRequest request) {
        return service.getAllProductGroups(request);
    }

    @Override
    public ProductGroupXmlObject findProductGroupById(ProductGroupRequest request) {
        return service.findProductGroupById(request);
    }

    @Override
    public ProductGroupXmlObject saveProductGroup(SaveProductGroupRequest request) {
        return service.saveProductGroup(request);
    }

    @Override
    public ProductGroupXmlObject updateProductGroup(ProductGroupXmlObject request) {
        return service.updateProductGroup(request);
    }

    @Override
    public ProductGroupXmlObject deleteProductGroup(DeleteProductGroupRequest request) {
        return service.deleteProductGroup(request);
    }
}
