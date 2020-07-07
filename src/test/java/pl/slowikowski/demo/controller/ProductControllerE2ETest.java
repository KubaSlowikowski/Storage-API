package pl.slowikowski.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.service.ProductRepositoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
class ProductControllerE2ETest {

    @LocalServerPort //numer wylosowanego portu
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //klasa do odpytywanie usług (np. strzela GET-em)

    @Autowired
    ProductRepositoryImpl service;

    @Test
    void httpGet_returnsAllProducts() {
        //given
        final int initialSize = service.findAll().size();
        service.save(new Product("foo", "bar", 1));
        service.save(new Product("foo2", "bar2", 2));

        //when
        Product[] result = restTemplate.getForObject("http://localhost:" + port + "/products", Product[].class);

        //then
        assertThat(result).hasSize(2);
    }

}