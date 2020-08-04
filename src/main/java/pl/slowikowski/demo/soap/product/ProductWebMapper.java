package pl.slowikowski.demo.soap.product;

import org.mapstruct.Mapper;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.soap.CommonWebMapper;
import pl.slowikowski.jakub.soap_example.product.Product;

@Mapper(componentModel = "spring")
public interface ProductWebMapper extends CommonWebMapper<ProductDTO, Product> {
}
