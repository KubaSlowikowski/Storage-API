package pl.slowikowski.demo.productGroup;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowikowski.demo.abstraction.AbstractEntity;
import pl.slowikowski.demo.product.Product;

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

    public static final class ProductGroupBuilder {
        private int id;
        private String name;
        private String description;
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
