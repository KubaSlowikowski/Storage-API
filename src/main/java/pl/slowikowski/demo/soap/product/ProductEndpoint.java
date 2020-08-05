package pl.slowikowski.demo.soap.product;

import org.springframework.data.domain.Page;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.jakub.soap_example.product.*;

import java.util.List;

@Endpoint
public class ProductEndpoint {

    private final ProductService service;
    private final ProductWebMapper mapper;
    private final String NAMESPACE_URI = "http://jakub.slowikowski.pl/soap-example/product";

    public ProductEndpoint(final ProductService service, final ProductWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        Page<ProductDTO> result = service.getAll(mapper.toPageFromGetAllRequest(request), request.getSearch());
        var response = mapper.toGetAllProductsResponse(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindProductRequest")
    @ResponsePayload
    public ProductObject findProductById(@RequestPayload FindProductRequest request) {
        ProductDTO result = service.findById(request.getId());
        var response = mapper.toWebObject(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveProductRequest")
    @ResponsePayload
    public ProductObject saveProduct(@RequestPayload SaveProductRequest request) {
        var productDTO = mapper.toDtoFromSaveRequest(request);
        ProductDTO result = service.save(productDTO);
        var response = mapper.toWebObject(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductObject")
    @ResponsePayload
    public ProductObject updateProduct(@RequestPayload ProductObject request) {
        var productDTO = mapper.toDto(request);
        ProductDTO result = service.update(productDTO.getId(), productDTO);
        var response = mapper.toWebObject(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteProductRequest")
    @ResponsePayload
    public ProductObject deleteProduct(@RequestPayload DeleteProductRequest request) {
        ProductDTO result = service.delete(request.getId());
        var response = mapper.toWebObject(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductsInGroupRequest")
    @ResponsePayload
    public ProductList findAllProductsByGroupId(@RequestPayload ProductsInGroupRequest request) {
        List<ProductDTO> products = service.findAllByGroupId(request.getId());
        var result = mapper.toWebList(products);
        ProductList response = new ProductList();
        response.getProducts().addAll(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BuyProductRequest")
    @ResponsePayload
    public ProductObject buyProduct(@RequestPayload BuyProductRequest request) {
        ProductDTO result = service.buyProduct(request.getId());
        var response = mapper.toWebObject(result);
        return response;
    }
}
