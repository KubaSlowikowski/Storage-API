package pl.slowikowski.demo.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ProductDTO {
    @Positive
    private int id;
    @NotBlank(message = "Product's name must be not null and not be empty")
    private String name;
    @NotBlank(message = "Product's description must be not null and not be empty")
    private String description;
    @PositiveOrZero
    private int price;
    private boolean sold;
    private int groupId;

    public void toogle() {
        this.sold = !this.sold;
    }

    public static final class ProductDTOBuilder {
        private int id;
        private String name;
        private String description;
        private int price;
        private boolean sold;
        private int groupId;

        private ProductDTOBuilder() {
        }

        public static ProductDTOBuilder aProductDTO() {
            return new ProductDTOBuilder();
        }

        public ProductDTOBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ProductDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductDTOBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public ProductDTOBuilder withSold(boolean sold) {
            this.sold = sold;
            return this;
        }

        public ProductDTOBuilder withGroupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public ProductDTO build() {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(id);
            productDTO.setName(name);
            productDTO.setDescription(description);
            productDTO.setPrice(price);
            productDTO.setSold(sold);
            productDTO.setGroupId(groupId);
            return productDTO;
        }
    }
}
