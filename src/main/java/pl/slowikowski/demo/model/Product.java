package pl.slowikowski.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Product's description must be not null and not be empty")
    private String description;
    private boolean sold;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "product_group_id") //jak pobieramy taska, pobieramy tez grupe
    private ProductGroup group;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
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

    public void updateFrom(final Product source) {
        this.id = source.id;
        this.description = source.description;
        this.group = source.group;
        this.sold = source.sold;
    }

    public void toogle() {
        this.sold = !this.sold;
    }
}