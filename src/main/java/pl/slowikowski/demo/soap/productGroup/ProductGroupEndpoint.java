package pl.slowikowski.demo.soap.productGroup;

import org.springframework.data.domain.Page;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.jakub.soap_example.product_group.*;

@Endpoint
public class ProductGroupEndpoint {

    private final ProductGroupService service;
    private final ProductGroupWebMapper mapper;
    private final String NAMESPACE_URI = "http://jakub.slowikowski.pl/soap-example/product-group";

    public ProductGroupEndpoint(final ProductGroupService service, final ProductGroupWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductGroupsRequest")
    @ResponsePayload
    public GetAllProductGroupsResponse getAllProductGroups(@RequestPayload GetAllProductGroupsRequest request) {
        Page<ProductGroupDTO> result = service.getAll(mapper.toPageFromGetAllRequest(request), request.getSearch());
        var response = mapper.toGetAllProductGroupsResponse(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductGroupRequest")
    @ResponsePayload
    public ProductGroupObject getProductGroupById(@RequestPayload ProductGroupRequest request) {
        ProductGroupDTO groupDTO = service.findById(request.getId());
        var response = mapper.toWebObject(groupDTO);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveProductGroupRequest")
    @ResponsePayload
    public ProductGroupObject saveProductGroup(@RequestPayload SaveProductGroupRequest request) {
        var groupDto = mapper.toDtoFromSaveRequest(request);
        ProductGroupDTO result = service.save(groupDto);
        var response = mapper.toWebObject(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductGroupObject")
    @ResponsePayload
    public ProductGroupObject updateProductGroup(@RequestPayload ProductGroupObject request) {
        var groupDto = mapper.toDto(request);
        ProductGroupDTO result = service.update(groupDto.getId(), groupDto);
        var response = mapper.toWebObject(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteProductGroupRequest")
    @ResponsePayload
    public ProductGroupObject deleteProductGroup(@RequestPayload DeleteProductGroupRequest request) {
        ProductGroupDTO result = service.delete(request.getId());
        var response = mapper.toWebObject(result);
        return response;
    }
}
