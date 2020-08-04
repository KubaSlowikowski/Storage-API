package pl.slowikowski.demo.soap.productGroup;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.jakub.soap_example.product_group.GetProductGroup;
import pl.slowikowski.jakub.soap_example.product_group.GetProductGroupResponse;

@Endpoint
public class ProductGroupEndpoint {

    private final ProductGroupService service;
    private final ProductGroupWebMapper mapper;
    private final String NAMESPACE_URI = "http://jakub.slowikowski.pl/soap-example/product-group";


    public ProductGroupEndpoint(final ProductGroupService service, final ProductGroupWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductGroup")
    @ResponsePayload
    public GetProductGroupResponse getProductGroupById(@RequestPayload GetProductGroup getProductGroup) {
        ProductGroupDTO groupDTO = service.findById(getProductGroup.getId());
        var result = mapper.fromDto(groupDTO);
        GetProductGroupResponse getResponse = new GetProductGroupResponse();
        getResponse.setProductGroup(result);
        return getResponse;
    }



}
