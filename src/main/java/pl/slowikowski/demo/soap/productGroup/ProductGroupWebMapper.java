package pl.slowikowski.demo.soap.productGroup;

import org.mapstruct.Mapper;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.soap.CommonWebMapper;
import pl.slowikowski.jakub.soap_example.product_group.ProductGroup;
import pl.slowikowski.jakub.soap_example.product_group.ProductGroupObject;
import pl.slowikowski.jakub.soap_example.product_group.SaveProductGroupRequest;

@Mapper(componentModel = "spring")
public interface ProductGroupWebMapper extends CommonWebMapper<ProductGroupDTO, ProductGroupObject, ProductGroup> {
    ProductGroupDTO toDtoFromSaveRequest(SaveProductGroupRequest saveRequest);
}
