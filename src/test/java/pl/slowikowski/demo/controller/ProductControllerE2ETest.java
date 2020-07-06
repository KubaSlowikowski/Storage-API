package pl.slowikowski.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.slowikowski.demo.service.ProductRepositoryImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerE2ETest {

    @LocalServerPort //numer wylosowanego portu
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //klasa do odpytywanie usług (np. strzela GET-em)

    @Autowired
    ProductRepositoryImpl service;

//    @Test
//    //given
//    final int initialSize = service.findAll().size();
//    //when
//
//    //then

}
