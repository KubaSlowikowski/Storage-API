package pl.slowikowski.demo.model;

import pl.slowikowski.demo.persistence.model.Product;

import javax.validation.constraints.PositiveOrZero;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Product toProduct() {
        return new Product(name, description, price);
    }
}
