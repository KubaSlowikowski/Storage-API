package pl.slowikowski.demo.soap.product;

import org.springframework.data.domain.Page;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product.*;

import java.util.List;

@Endpoint
public class ProductEndpoint {

    private final ProductService service;
    private final ProductWebMapper mapper;
    private static final String NAMESPACE_URI = "http://jakub.slowikowski.pl/soap-example/product";

    public ProductEndpoint(final ProductService service, final ProductWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductsRequest")
    @ResponsePayload
    public GetAllResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        Page<ProductDTO> result = service.getAll(mapper.toPageFromPageXml(request.getPageable()), request.getSearch());
        return mapper.toGetAllResponse(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindProductRequest")
    @ResponsePayload
    public ProductXmlObject findProductById(@RequestPayload FindProductRequest request) {
        ProductDTO result = service.findById(request.getId());
        return mapper.toWebObject(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveProductRequest")
    @ResponsePayload
    public ProductXmlObject saveProduct(@RequestPayload SaveProductRequest request) {
        var productDTO = mapper.toDtoFromSaveRequest(request);
        ProductDTO result = service.save(productDTO);
        return mapper.toWebObject(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductXmlObject")
    @ResponsePayload
    public ProductXmlObject updateProduct(@RequestPayload ProductXmlObject request) {
        var productDTO = mapper.toDto(request);
        ProductDTO result = service.update(productDTO.getId(), productDTO);
        return mapper.toWebObject(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteProductRequest")
    @ResponsePayload
    public ProductXmlObject deleteProduct(@RequestPayload DeleteProductRequest request) {
        ProductDTO result = service.delete(request.getId());
        return mapper.toWebObject(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductsInGroupRequest")
    @ResponsePayload
    public ProductXmlList findAllProductsByGroupId(@RequestPayload ProductsInGroupRequest request) {
        List<ProductDTO> products = service.findAllByGroupId(request.getId());
        var result = mapper.toWebList(products);
        ProductXmlList response = new ProductXmlList();
        response.getProducts().addAll(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BuyProductRequest")
    @ResponsePayload
    public ProductXmlObject buyProduct(@RequestPayload BuyProductRequest request) {
        ProductDTO result = service.buyProduct(request.getId());
        return mapper.toWebObject(result);
    }
}
