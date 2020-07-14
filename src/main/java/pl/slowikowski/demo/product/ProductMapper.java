package pl.slowikowski.demo.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "group.id", target = "groupId")
    ProductDTO productToProductDto(Product product);

    @Mapping(source = "groupId", target = "group.id")
    Product productDtoToProduct(ProductDTO productDTO);

    Set<ProductDTO> productSetToProductDtoSet(Set<Product> productSet);

    List<ProductDTO> productsListToProductDtoList(List<Product> productList);
}
