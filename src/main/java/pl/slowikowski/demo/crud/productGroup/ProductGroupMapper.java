package pl.slowikowski.demo.crud.productGroup;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.crud.abstraction.CommonMapper;
import pl.slowikowski.demo.crud.product.ProductMapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProductGroupMapper extends CommonMapper<ProductGroup, ProductGroupDTO> {
    ProductGroupMapper INSTANCE = Mappers.getMapper(ProductGroupMapper.class);

    @AfterMapping
    default void afterMappingFromDTO(ProductGroupDTO source, @MappingTarget ProductGroup target) {
        if (target.getProducts() != null) {
            target.getProducts().forEach(product -> product.setGroup(target));
        }
    }
}
