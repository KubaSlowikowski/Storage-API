package pl.slowikowski.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.slowikowski.demo.model.ProductDTO;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.service.ProductRepositoryImpl;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("integration")
class ProductControllerLightIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepositoryImpl service;

    @Test
    void httpGet_returnsGivenProduct() throws Exception {
        //given
        final String name = "foo";
        final String description = "bar";
        final int price = 123;
        when(service.findById(anyInt()))
                .thenReturn(new ProductDTO(new Product(name, description, price)));

        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/123"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(name)))
                .andExpect(content().string(containsString(description)))
                .andExpect(content().string(containsString(String.valueOf(price))));
    }

    @Test
    void httpGet_returnsAllProducts() throws Exception {
        //given
        final String name = "foo";
        final String description = "bar";
        final int price = 123;


        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/123"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(name)))
                .andExpect(content().string(containsString(description)))
                .andExpect(content().string(containsString(String.valueOf(price))));
    }
}
