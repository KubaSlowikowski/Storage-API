package pl.slowikowski.demo.soap.product;

import org.mapstruct.Mapper;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.soap.CommonWebMapper;
import pl.slowikowski.jakub.soap_example.product.ProductXml;
import pl.slowikowski.jakub.soap_example.product.ProductXmlObject;
import pl.slowikowski.jakub.soap_example.product.SaveProductRequest;

@Mapper(componentModel = "spring")
public interface ProductWebMapper extends CommonWebMapper<ProductDTO, ProductXmlObject, ProductXml> {
    ProductDTO toDtoFromSaveRequest(SaveProductRequest saveRequest);
}
