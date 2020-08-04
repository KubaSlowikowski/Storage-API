package pl.slowikowski.demo.soap.product;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.jakub.soap_example.product.GetProduct;
import pl.slowikowski.jakub.soap_example.product.GetProductResponse;

@Endpoint
public class ProductEndpoint {

    private final ProductService service;
    private final ProductWebMapper mapper;
    private final String NAMESPACE_URI = "http://jakub.slowikowski.pl/soap-example/product";

    public ProductEndpoint(final ProductService service, final ProductWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProduct")
    @ResponsePayload
    public GetProductResponse getProductById(@RequestPayload GetProduct getProduct) {
        ProductDTO product = service.findById(getProduct.getId());
        var result = mapper.fromDto(product);
        GetProductResponse getResponse = new GetProductResponse();
        getResponse.setProduct(result);
        return getResponse;
    }
}
