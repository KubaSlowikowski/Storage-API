package pl.slowikowski.demo.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.jakub.soap_example.GetProduct;
import pl.slowikowski.jakub.soap_example.GetResponse;

@Endpoint
public class ProductEndpoint {

    private final ProductService service;
    private final ProductWebMapper mapper;

    public ProductEndpoint(final ProductService service, final ProductWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = "http://jakub.slowikowski.pl/soap-example", localPart = "getProduct")
    @ResponsePayload
    public GetResponse getProductById(@RequestPayload GetProduct getProduct) {
        ProductDTO product = service.findById(getProduct.getId());
        var result = mapper.fromDto(product);
        GetResponse getResponse = new GetResponse();
        getResponse.setProduct(result);
        return getResponse;
    }
}
