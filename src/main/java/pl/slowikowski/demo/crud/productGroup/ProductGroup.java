package pl.slowikowski.demo.crud.productGroup;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowikowski.demo.crud.abstraction.AbstractEntity;
import pl.slowikowski.demo.crud.product.Product;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Entity
@Table(name = "product_groups")
@NoArgsConstructor
@Getter
@Setter
public class ProductGroup extends AbstractEntity {

    private String name;
    private String description;
    @Valid
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "group", fetch = FetchType.LAZY)
    private Set<Product> products;

    @Override
    public String toString() {
        return "ProductGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductGroup that = (ProductGroup) o;

        if (getId() != that.getId()) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return products != null ? products.equals(that.products) : that.products == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (31 * getId());
        return result;
    }

    public static final class ProductGroupBuilder {
        private Long id;
        private String name;
        private String description;
        private Set<Product> products;

        private ProductGroupBuilder() {
        }

        public static ProductGroupBuilder aProductGroup() {
            return new ProductGroupBuilder();
        }

        public ProductGroupBuilder withId(Long id) {
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

        public ProductGroupBuilder withProducts(Set<Product> products) {
            this.products = products;
            return this;
        }

        public ProductGroup build() {
            ProductGroup productGroup = new ProductGroup();
            productGroup.setId(id);
            productGroup.setName(name);
            productGroup.setDescription(description);
            productGroup.setProducts(products);
            return productGroup;
        }
    }
}
