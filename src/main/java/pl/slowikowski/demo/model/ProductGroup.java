package pl.slowikowski.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "product_groups")
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Product group's description must be not null and not be empty")
    private String description;
    @Embedded
    private Audit audit = new Audit();
    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    //CascadeType.ALL - gdy usune grupe, usuwam wszystkie produkty, mappedBy - wewnątrz każdego produktu ta grupa jest zmapowana jako 'group'
    private Set<Product> products;

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(final Set<Product> products) {
        this.products = products;
    }

    public void updateFrom(final ProductGroup source) {
        this.id = source.id;
        this.description = source.description;
        this.products = source.products;
    }
}
