package pl.slowikowski.demo.productGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ProductGroupMapperDecorator implements ProductGroupMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductGroupMapper delegate;

    @Override
    public ProductGroup fromDto(ProductGroupDTO dto) {
        ProductGroup entity = delegate.fromDto(dto);
        if (entity.getProducts() != null) {
            entity.getProducts().forEach(product -> product.setGroup(entity));
        }
        return entity;
    }
}
