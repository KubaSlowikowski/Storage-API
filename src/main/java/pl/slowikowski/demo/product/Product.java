package pl.slowikowski.demo.product;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowikowski.demo.audit.Audit;
import pl.slowikowski.demo.productGroup.ProductGroup;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private int price;
    private boolean sold;
    @ManyToOne
    @JoinColumn(name = "product_group_id") //jak pobieramy taska, pobieramy tez grupe
    private ProductGroup group;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                ", group=" + group +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (sold != product.sold) return false;
        if (!name.equals(product.name)) return false;
        return description.equals(product.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price;
        result = 31 * result + (sold ? 1 : 0);
        return result;
    }

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
