package pl.slowikowski.demo.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest(ProductController.class)
@ActiveProfiles("integration")
class ProductControllerLightIntegrationTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductRepositoryImpl service;
//
//    @Test
//    void httpGet_returnsGivenProduct() throws Exception {
//        //given
//        final String name = "foo";
//        final String description = "bar";
//        final int price = 123;
//        when(service.findById(anyInt()))
//                .thenReturn(new ProductDTO(new Product(name, description, price)));
//
//        //when + then
//        mockMvc.perform(MockMvcRequestBuilders.get("/products/123"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString(name)))
//                .andExpect(content().string(containsString(description)))
//                .andExpect(content().string(containsString(String.valueOf(price))));
//    }
//
//    @Test
//    void httpGet_returnsAllProducts() throws Exception {
//        //given
//        final String name = "foo";
//        final String description = "bar";
//        final int price = 123;
//
//
//        //when + then
//        mockMvc.perform(MockMvcRequestBuilders.get("/products/123"))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString(name)))
//                .andExpect(content().string(containsString(description)))
//                .andExpect(content().string(containsString(String.valueOf(price))));
//    }
}
