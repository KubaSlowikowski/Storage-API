package pl.slowikowski.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.persistence.repository.ProductRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("testing")
class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository; //repo z pamieci

    @Test
    void httpGet_returnsProductById() throws Exception {
        //given
        int id = repository.save(new Product("foo", "bar", 123)).getId();

        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + id))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"name\":\"foo\",\"description\":\"bar\",\"price\":123,\"sold\":false}"));
    }

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        int id = repository.save(new Product("foo", "bar", 123)).getId();

        //when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + id))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"name\":\"foo\",\"description\":\"bar\",\"price\":123,\"sold\":false}"));
    }
}
