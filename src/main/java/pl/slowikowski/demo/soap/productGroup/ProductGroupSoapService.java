package pl.slowikowski.demo.soap.productGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupService;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product_group.*;

@Service
@RequiredArgsConstructor
public class ProductGroupSoapService {

    private final ProductGroupService service;
    private final ProductGroupWebMapper mapper;

    public GetAllResponse getAllProductGroups(GetAllProductGroupsRequest request) {
        Page<ProductGroupDTO> result = service.getAll(mapper.toPageFromPageXml(request.getPageable()), request.getSearch());
        return mapper.toGetAllResponse(result);
    }

    public ProductGroupXmlObject findProductGroupById(ProductGroupRequest request) {
        ProductGroupDTO groupDTO = service.findById(request.getId());
        return mapper.toWebObject(groupDTO);
    }

    public ProductGroupXmlObject saveProductGroup(SaveProductGroupRequest request) {
        var groupDto = mapper.toDtoFromSaveRequest(request);
        ProductGroupDTO result = service.save(groupDto);
        return mapper.toWebObject(result);
    }

    public ProductGroupXmlObject updateProductGroup(ProductGroupXmlObject request) {
        var groupDto = mapper.toDto(request);
        ProductGroupDTO result = service.update(groupDto.getId(), groupDto);
        return mapper.toWebObject(result);
    }

    public ProductGroupXmlObject deleteProductGroup(DeleteProductGroupRequest request) {
        ProductGroupDTO result = service.delete(request.getId());
        return mapper.toWebObject(result);
    }
}