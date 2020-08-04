package pl.slowikowski.demo.soap.product;

import org.mapstruct.Mapper;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.soap.CommonWebMapper;
import pl.slowikowski.jakub.soap_example.product.Product;
import pl.slowikowski.jakub.soap_example.product.ProductObject;
import pl.slowikowski.jakub.soap_example.product.SaveProductRequest;

@Mapper(componentModel = "spring")
public interface ProductWebMapper extends CommonWebMapper<ProductDTO, ProductObject, Product> {
    ProductDTO toDtoFromSaveRequest(SaveProductRequest saveRequest);
}
