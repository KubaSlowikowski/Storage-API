package pl.slowikowski.demo.soap.productGroup;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.soap.CommonWebMapper;
import pl.slowikowski.jakub.soap_example.product_group.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductGroupWebMapper extends CommonWebMapper<ProductGroupDTO, ProductGroupObject, ProductGroup> {
    ProductGroupDTO toDtoFromSaveRequest(SaveProductGroupRequest saveRequest);

    default PageRequest toPageFromGetAllRequest(GetAllProductGroupsRequest request) {
        var pageable = request.getPageable();

        if (pageable.getDir() == null) {
            pageable.setDir("ASC");
        }
        if (pageable.getSort() == null) {
            pageable.setSort("id");
        }
        return PageRequest.of(pageable.getPage(), pageable.getSize(), Sort.by(Sort.Direction.valueOf(pageable.getDir().toUpperCase()), pageable.getSort()));
    }

    default GetAllProductGroupsResponse toGetAllProductGroupsResponse(Page<ProductGroupDTO> page) {
        List<ProductGroup> content = new ArrayList<>();
        page.getContent().forEach(productGroupDTO -> content.add(this.toWeb(productGroupDTO)));
        GetAllProductGroupsResponse result = new GetAllProductGroupsResponse();
        result.getContent().addAll(content);
        result.setTotalPages(page.getTotalElements());
        result.setTotalElements(page.getTotalElements());
        result.setNumber(page.getNumber());
        result.setSize(page.getSize());
        result.setSorted(page.getSort().isSorted());
        return result;
    }
}
