package pl.slowikowski.demo.model;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.persistence.model.Product;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    @PositiveOrZero
    private int price;
    private boolean sold;

    public ProductDTO(Product source) {
        this.name = source.getName();
        this.description = source.getDescription();
        this.price = source.getPrice();
        this.sold = source.isSold();
    }

    public Product toProduct() {
        return new Product(name, description, price);
    }
}
