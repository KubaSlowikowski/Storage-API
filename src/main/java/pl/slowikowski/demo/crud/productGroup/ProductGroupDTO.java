package pl.slowikowski.demo.crud.productGroup;

import lombok.Data;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;
import pl.slowikowski.demo.crud.product.ProductDTO;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class ProductGroupDTO extends AbstractDto {
    @NotBlank(message = "Products group's name must be not null and not be empty.")
    private String name;
    @NotBlank(message = "Product group's description must be not null and not be empty.")
    private String description;
    private Set<ProductDTO> products;

    @Override
    public String toString() {
        return "ProductGroupDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductGroupDTO that = (ProductGroupDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return products != null ? products.equals(that.products) : that.products == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (31 * getId());
        return result;
    }

    public static final class ProductGroupDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private Set<ProductDTO> products;

        private ProductGroupDTOBuilder() {
        }

        public static ProductGroupDTOBuilder aProductGroupDTO() {
            return new ProductGroupDTOBuilder();
        }

        public ProductGroupDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProductGroupDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductGroupDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductGroupDTOBuilder withProducts(Set<ProductDTO> products) {
            this.products = products;
            return this;
        }

        public ProductGroupDTO build() {
            ProductGroupDTO productGroupDTO = new ProductGroupDTO();
            productGroupDTO.setId(id);
            productGroupDTO.setName(name);
            productGroupDTO.setDescription(description);
            productGroupDTO.setProducts(products);
            return productGroupDTO;
        }
    }
}
