package pl.slowikowski.demo.persistence.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.slowikowski.demo.model.ProductGroupDTO;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Entity
@Table(name = "product_groups")
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@NotBlank(message = "Products group's name must be not null and not be empty")
    private String name;
    //@NotBlank(message = "Product group's description must be not null and not be empty")
    private String description;
    @Embedded
    private Audit audit = new Audit();
    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", orphanRemoval = true)
    //CascadeType.ALL - gdy usune grupe, usuwam wszystkie produkty, mappedBy - wewnątrz każdego produktu ta grupa jest zmapowana jako 'group'
    @JsonIgnore
    private Set<Product> products;

    public ProductGroup(String name, String description, Set<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(final Set<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateFrom(ProductGroup source) {
        if (source.name != null) {
            this.name = source.name;
        }
        if (source.description != null) {
            this.description = source.description;
        }
        if(source.products != null) {
            this.products = source.products;
        }
    }

    public ProductGroupDTO toDTO() {
        return new ProductGroupDTO(this);
    }
}