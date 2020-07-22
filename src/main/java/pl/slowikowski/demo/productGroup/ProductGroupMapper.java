package pl.slowikowski.demo.productGroup;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import pl.slowikowski.demo.abstraction.CommonMapper;
import pl.slowikowski.demo.product.ProductMapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ProductGroupMapper extends CommonMapper<ProductGroup, ProductGroupDTO> {
    ProductGroupMapper INSTANCE = Mappers.getMapper(ProductGroupMapper.class);

    @AfterMapping
    default void afterMappingFromDTO(ProductGroupDTO source, @MappingTarget ProductGroup target) {
        if (target.getProducts() != null) {
            target.getProducts().forEach(product -> product.setGroup(target));
        }
    }
}
