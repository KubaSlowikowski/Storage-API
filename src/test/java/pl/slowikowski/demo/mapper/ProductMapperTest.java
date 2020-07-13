package pl.slowikowski.demo.mapper;

import org.junit.jupiter.api.Test;
import pl.slowikowski.demo.entity.Product;
import pl.slowikowski.demo.model.ProductDTO;

import static org.junit.jupiter.api.Assertions.*;
import static pl.slowikowski.demo.utils.Utils.*;

class ProductMapperTest {

    ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    void should_map_product_to_productDto() {
        //given
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        //when
        ProductDTO result = productMapper.productToProductDto(product);

        //then
        assertEquals(productDTO,result);
    }

    @Test
    void should_map_productDto_to_product() {
        //given
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        //when
        Product result = productMapper.productDtoToProduct(productDTO);

        //then
        assertEquals(product, result);
    }

    @Test
    void shouldnt_map_product_to_productDto() {
        //given
        Product product = getProduct();
        product.setId(123);
        ProductDTO productDTO = getProductDto();

        //when
        ProductDTO result = productMapper.productToProductDto(product);

        //then
        assertNotEquals(productDTO,result);
    }

    @Test
    void shouldnt_map_productDto_to_product() {
        //given
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();
        productDTO.setName("foo");

        //when
        Product result = productMapper.productDtoToProduct(productDTO);

        //then
        assertNotEquals(product, result);
    }

    @Test
    void should_map_correctly() {
        //given
        var toTest = getProduct();
        var product = getProduct();
        product.setGroup(getProductGroup());
        product.setGroup(null);

        //when
        var productDTO = productMapper.productToProductDto(product);
        var result  = productMapper.productDtoToProduct(productDTO);

        //then
        assertEquals(result, toTest);
    }
}
