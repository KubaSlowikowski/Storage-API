package pl.slowikowski.demo.soap.productGroup;

import org.springframework.data.domain.Page;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product_group.*;

@Endpoint
public class ProductGroupEndpoint {

    private final ProductGroupService service;
    private final ProductGroupWebMapper mapper;
    private static final String NAMESPACE_URI = "http://jakub.slowikowski.pl/soap-example/product-group";

    public ProductGroupEndpoint(final ProductGroupService service, final ProductGroupWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllProductGroupsRequest")
    @ResponsePayload
    public GetAllResponse getAllProductGroups(@RequestPayload GetAllProductGroupsRequest request) {
        Page<ProductGroupDTO> result = service.getAll(mapper.toPageFromPageXml(request.getPageable()), request.getSearch());
        return mapper.toGetAllResponse(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductGroupRequest")
    @ResponsePayload
    public ProductGroupXmlObject getProductGroupById(@RequestPayload ProductGroupRequest request) {
        ProductGroupDTO groupDTO = service.findById(request.getId());
        return mapper.toWebObject(groupDTO);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveProductGroupRequest")
    @ResponsePayload
    public ProductGroupXmlObject saveProductGroup(@RequestPayload SaveProductGroupRequest request) {
        var groupDto = mapper.toDtoFromSaveRequest(request);
        ProductGroupDTO result = service.save(groupDto);
        return mapper.toWebObject(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductGroupXmlObject")
    @ResponsePayload
    public ProductGroupXmlObject updateProductGroup(@RequestPayload ProductGroupXmlObject request) {
        var groupDto = mapper.toDto(request);
        ProductGroupDTO result = service.update(groupDto.getId(), groupDto);
        return mapper.toWebObject(result);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteProductGroupRequest")
    @ResponsePayload
    public ProductGroupXmlObject deleteProductGroup(@RequestPayload DeleteProductGroupRequest request) {
        ProductGroupDTO result = service.delete(request.getId());
        return mapper.toWebObject(result);
    }
}
