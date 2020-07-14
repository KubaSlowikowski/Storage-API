package pl.slowikowski.demo.productGroup;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.product.ProductMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ProductGroupMapper {

    ProductGroupMapper INSTANCE = Mappers.getMapper(ProductGroupMapper.class);

    ProductGroupDTO groupToGroupDto(ProductGroup productGroup);

    ProductGroup groupDtoToGroup(ProductGroupDTO productGroupDTO);

    List<ProductGroupDTO> groupListToGroupDtoList(List<ProductGroup> groupList);
}
