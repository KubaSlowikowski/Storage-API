package pl.slowikowski.demo.product;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowikowski.demo.abstraction.AbstractEntity;
import pl.slowikowski.demo.productGroup.ProductGroup;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product extends AbstractEntity {

    private String name;
    private String description;
    private int price;
    private boolean sold;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_id") //jak pobieramy taska, pobieramy tez grupe
    private ProductGroup group;

    public static final class ProductBuilder {
        private int id;
        private String name;
        private String description;
        private int price;
        private boolean sold;
        //jak pobieramy taska, pobieramy tez grupe
        private ProductGroup group;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withSold(boolean sold) {
            this.sold = sold;
            return this;
        }

        public ProductBuilder withGroup(ProductGroup group) {
            this.group = group;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setSold(sold);
            product.setGroup(group);
            return product;
        }
    }
}
