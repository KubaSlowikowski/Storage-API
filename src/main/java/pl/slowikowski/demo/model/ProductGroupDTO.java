package pl.slowikowski.demo.model;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.persistence.model.ProductGroup;

import java.util.Set;

@Getter
@Setter
public class ProductGroupDTO {
    private String name;
    private String description;
    private Set<Product> products;

    public ProductGroupDTO(ProductGroup source) {
        this.name = source.getName();
        this.description = source.getDescription();
        this.products = source.getProducts();
    }

    public ProductGroup toGroup() {
        return new ProductGroup(name, description, products);
    }
}