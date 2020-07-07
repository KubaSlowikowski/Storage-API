package pl.slowikowski.demo.persistence.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.slowikowski.demo.model.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@NotBlank(message = "Product's name must be not null and not be empty")
    private String name;
    //@NotBlank(message = "Product's description must be not null and not be empty")
    private String description;
    @PositiveOrZero
    private int price;
    private boolean sold;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "product_group_id") //jak pobieramy taska, pobieramy tez grupe
    private ProductGroup group;

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(final boolean sold) {
        this.sold = sold;
    }

    public ProductGroup getGroup() {
        return group;
    }

    public void setGroup(final ProductGroup group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void toogle() {
        this.sold = !this.sold;
    }

    public void updateFrom(Product source) {
        if (source.name != null) {
            this.name = source.name;
        }
        if (source.description != null) {
            this.description = source.description;
        }
        if (source.price > 0) {
            this.price = source.price;
        }
    }

    public ProductDTO toDTO() {
        return new ProductDTO(this);
    }
}