package pl.slowikowski.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.entity.ProductGroup;
import pl.slowikowski.demo.model.ProductGroupDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {

    ProductGroupMapper INSTANCE = Mappers.getMapper(ProductGroupMapper.class);

    ProductGroupDTO groupToGroupDto(ProductGroup productGroup);

    ProductGroup groupDtoToGroup(ProductGroupDTO productGroupDTO);

    List<ProductGroupDTO> groupListToGroupDtoList(List<ProductGroup> groupList);
}
