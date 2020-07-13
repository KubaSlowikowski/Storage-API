package pl.slowikowski.demo.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private int price;
    private boolean sold;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "product_group_id") //jak pobieramy taska, pobieramy tez grupe
    private ProductGroup group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (sold != product.sold) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        return group != null ? group.equals(product.group) : product.group == null;
    }

    @Override //BEZ AUDYTU
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (sold ? 1 : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                ", audit=" + audit +
                ", group=" + group +
                '}';
    }

    public static final class ProductBuilder {
        private int id;
        private String name;
        private String description;
        private int price;
        private boolean sold;
        private Audit audit = new Audit();
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

        public ProductBuilder withAudit(Audit audit) {
            this.audit = audit;
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
            product.setAudit(audit);
            product.setGroup(group);
            return product;
        }
    }
}
