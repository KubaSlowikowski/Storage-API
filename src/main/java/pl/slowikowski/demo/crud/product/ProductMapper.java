package pl.slowikowski.demo.crud.product;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.crud.abstraction.CommonMapper;

@Mapper(componentModel = "spring")
//@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper extends CommonMapper<Product, ProductDTO> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Override
    @Mapping(source = "group.id", target = "groupId")
    ProductDTO toDto(Product product);

    @Override
    @Mapping(source = "groupId", target = "group.id")
    Product fromDto(ProductDTO dto);

    @BeforeMapping
    default void beforeMappingFromDTO(ProductDTO source) {
        if (source.getGroupId() == 0) {
            source.setGroupId(1);
        }
    }
}
