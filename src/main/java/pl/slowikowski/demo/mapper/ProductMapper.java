package pl.slowikowski.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.entity.Product;
import pl.slowikowski.demo.model.ProductDTO;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto(Product product);

    Product productDtoToProduct(ProductDTO productDTO);

    Set<ProductDTO> productSetToProductDtoSet(Set<Product> productSet);

    List<ProductDTO> productsListToProductDtoList(List<Product> productList);

    List<Product> productsDtoListToProductList(List<ProductDTO> productDooList);
}
