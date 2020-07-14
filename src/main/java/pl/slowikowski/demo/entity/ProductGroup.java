package pl.slowikowski.demo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Entity
@Table(name = "product_groups")
@NoArgsConstructor
@Getter
@Setter
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
//    @JsonIgnore
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductGroup that = (ProductGroup) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return products != null ? products.equals(that.products) : that.products == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", audit=" + audit +
                ", products=" + products +
                '}';
    }

    public static final class ProductGroupBuilder {
        private int id;
        private String name;
        private String description;
        private Audit audit = new Audit();
        private Set<Product> products;

        private ProductGroupBuilder() {
        }

        public static ProductGroupBuilder aProductGroup() {
            return new ProductGroupBuilder();
        }

        public ProductGroupBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ProductGroupBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductGroupBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductGroupBuilder withAudit(Audit audit) {
            this.audit = audit;
            return this;
        }

        public ProductGroupBuilder withProducts(Set<Product> products) {
            this.products = products;
            return this;
        }

        public ProductGroup build() {
            ProductGroup productGroup = new ProductGroup();
            productGroup.setId(id);
            productGroup.setName(name);
            productGroup.setDescription(description);
            productGroup.setAudit(audit);
            productGroup.setProducts(products);
            return productGroup;
        }
    }
}
