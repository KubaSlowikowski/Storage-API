package pl.slowikowski.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ProductMapperDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper delegate;

    @Override
    public Product fromDto(ProductDTO dto) {
        if (dto.getGroupId() == 0) {
            dto.setGroupId(1);
        }
        return delegate.fromDto(dto);
    }
}
