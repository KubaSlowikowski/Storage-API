package pl.slowikowski.demo.soap;

import org.mapstruct.Mapper;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.jakub.soap_example.Product;

@Mapper(componentModel = "spring")
public interface ProductWebMapper extends CommonWebMapper<ProductDTO, Product> {
}
