//package pl.slowikowski.demo.product.controller;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import pl.slowikowski.demo.crud.product.Product;
//import pl.slowikowski.demo.crud.product.ProductDTO;
//import pl.slowikowski.demo.crud.product.ProductMapper;
//import pl.slowikowski.demo.crud.product.ProductRepository;
//import pl.slowikowski.demo.crud.productGroup.ProductGroup;
//import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("testing")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class ProductControllerE2ETest {
//
//    @Autowired
//    private TestRestTemplate restTemplate; //klasa do odpytywanie usług (np. strzela GET-em)
//
//    @Autowired
//    private ProductMapper mapper = ProductMapper.INSTANCE;
//
//    @Autowired
//    private ProductRepository repository;
//
//    @Autowired
//    private ProductGroupRepository groupRepository;
//
//    private static String URL_PREFIX = "http://localhost:8080";
//
//    private Product bike;
//    private Product car;
//
//    private ProductDTO bikeDto;
//    private ProductDTO carDto;
//
//    @BeforeAll
//    void init() {
//        groupRepository.save(new ProductGroup());
//
//        bikeDto = ProductDTO.ProductDTOBuilder.aProductDTO()
//                .withName("bike")
//                .withDescription("bike")
//                .withPrice(100)
//                .build();
//        bike = repository.save(mapper.fromDto(bikeDto));
//
//        carDto = ProductDTO.ProductDTOBuilder.aProductDTO()
//                .withName("car")
//                .withDescription("car")
//                .withPrice(200)
//                .build();
//        car = repository.save(mapper.fromDto(carDto));
//    }
//
//    @AfterAll
//    void cleanUp() { // to avoid constraint errors when droping database after tests
//        repository.deleteAll();
//        groupRepository.deleteAll();
//    }
//
//    @Test
//    void should_saveProduct_andAssignDefaultGroupToIt() {
//        ProductDTO toSave = bikeDto;
//
//        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(URL_PREFIX + "/api/products", toSave, ProductDTO.class);
//
//        assertThat(response.getStatusCode().is2xxSuccessful());
//        assertThat(response.getBody()).isEqualTo(mapper.toDto(bike));
//
//
//    }
//
//
//}
