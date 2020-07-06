package pl.slowikowski.demo.model;

import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.persistence.model.ProductGroup;

import java.util.Set;

public class ProductGroupDTO {
    private String name;
    private String description;
    private Set<Product> products;

    public ProductGroupDTO(ProductGroup source) {
        this.name = source.getName();
        this.description = source.getDescription();
        this.products = source.getProducts();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ProductGroup toGroup() {
        return new ProductGroup(name, description, products);
    }
}