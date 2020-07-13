package pl.slowikowski.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
public class ProductGroupDTO {
    @Positive
    private int id;
    @NotBlank(message = "Products group's name must be not null and not be empty")
    private String name;
    @NotBlank(message = "Product group's description must be not null and not be empty")
    private String description;
    private Set<ProductDTO> products;

    public static final class ProductGroupDTOBuilder {
        private int id;
        private String name;
        private String description;
        private Set<ProductDTO> products;

        private ProductGroupDTOBuilder() {
        }

        public static ProductGroupDTOBuilder aProductGroupDTO() {
            return new ProductGroupDTOBuilder();
        }

        public ProductGroupDTOBuilder withId(int id) {
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
