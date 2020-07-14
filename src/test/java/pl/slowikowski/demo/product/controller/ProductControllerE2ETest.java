package pl.slowikowski.demo.product.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import pl.slowikowski.demo.product.Product;
import pl.slowikowski.demo.product.ProductServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.slowikowski.demo.utils.Utils.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
class ProductControllerE2ETest {

    @LocalServerPort //numer wylosowanego portu
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //klasa do odpytywanie usług (np. strzela GET-em)

    @Autowired
    ProductServiceImpl service;

//    @Test
//    void httpGet_returnsAllProducts() {
//        //given
//        final int initialSize = service.findAllProducts(Pageable.unpaged()).size();
//
//        service.saveProduct(getProductDto());
//        service.saveProduct(getSecondProductDto());
//
//        //when
//        Product[] result = restTemplate.getForObject("http://localhost:" + port + "/products", Product[].class);
//
//        //then
//        assertThat(result).hasSize(initialSize + 2); //FIXME - czasami initialSize = 1 <-- dlaczego?
//    }

    @Test
    void httpGet_returnsSpecificProduct() {
        //given
        final int initialSize = service.findAllProducts(Pageable.unpaged()).size();
        final int id = service.saveProduct(getProductDto()).getId();

        //when
        Product result = restTemplate.getForObject("http://localhost:" + port + "/products/" + id, Product.class);

        //then
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("description", description)
                .hasFieldOrPropertyWithValue("price", price);
        assertThat(service.findAllProducts(Pageable.unpaged()).size()).isEqualTo(initialSize + 1);
    }

}
