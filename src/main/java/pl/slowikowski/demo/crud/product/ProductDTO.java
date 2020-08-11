package pl.slowikowski.demo.crud.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ProductDTO extends AbstractDto {
    @NotBlank(message = "Product's name must be not null and not be empty.")
    private String name;
    @NotBlank(message = "Product's description must be not null and not be empty.")
    private String description;
    @PositiveOrZero(message = "Product's price must be positive or zero.")
    private int price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean sold;
    @Positive
    private Long groupId;

    public void toogle() {
        this.sold = !this.sold;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDTO that = (ProductDTO) o;
        if (getId() != that.getId()) return false;
        if (price != that.price) return false;
        if (sold != that.sold) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (31 * getId());
        return result;
    }

    public static final class ProductDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private int price;
        private boolean sold;
        private Long groupId;

        private ProductDTOBuilder() {
        }

        public static ProductDTOBuilder aProductDTO() {
            return new ProductDTOBuilder();
        }

        public ProductDTOBuilder withId(Long id) {
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

        public ProductDTOBuilder withGroupId(Long groupId) {
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
