package pl.slowikowski.demo.soap.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product.*;

import javax.jws.WebService;

@Controller
@WebService(endpointInterface = "pl.slowikowski.jakub.soap_example.product.ProductClient")
@RequiredArgsConstructor
public class ProductSoapController implements ProductClient {

    private final ProductSoapService service;

    @Override
    public GetAllResponse getAllProducts(GetAllProductsRequest request) {
        return service.getAllProducts(request);
    }

    @Override
    public ProductXmlObject deleteProduct(DeleteProductRequest request) {
        return service.deleteProduct(request);
    }

    @Override
    public ProductXmlObject findProduct(FindProductRequest request) {
        return service.findProduct(request);
    }

    @Override
    public GetAllResponse productsInGroup(ProductsInGroupRequest request) {
        return service.productsInGroup(request);
    }

    @Override
    public ProductXmlObject saveProduct(SaveProductRequest request) {
        return service.saveProduct(request);
    }

    @Override
    public ProductXmlObject buyProduct(BuyProductRequest request) {
        return service.buyProduct(request);
    }

    @Override
    public ProductXmlObject updateProduct(ProductXmlObject request) {
        return service.updateProduct(request);
    }
}
