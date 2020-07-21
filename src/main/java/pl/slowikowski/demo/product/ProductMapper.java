package pl.slowikowski.demo.product;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.abstraction.CommonMapper;

@Mapper(componentModel = "spring")
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper extends CommonMapper<Product, ProductDTO> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Override
    @Mapping(source = "group.id", target = "groupId")
    ProductDTO toDto(Product product);

    @Override
    @Mapping(source = "groupId", target = "group.id")
    Product fromDto(ProductDTO dto);
}
