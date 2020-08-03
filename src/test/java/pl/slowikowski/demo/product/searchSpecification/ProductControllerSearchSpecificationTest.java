package pl.slowikowski.demo.product.searchSpecification;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.slowikowski.demo.crud.product.Product;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductMapper;
import pl.slowikowski.demo.crud.product.ProductRepository;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;
import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
        //aby @BeforeAll nie musialo byc static
class ProductControllerSearchSpecificationTest {
    @Autowired
    ProductRepository repository;

    @Autowired
    ProductGroupRepository groupRepository;

    @Autowired
    ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    private TestRestTemplate restTemplate;

    private Product bike;
    private Product car;

    private final String URL_PREFIX = "http://localhost:8080/products/search?search=";

    @BeforeAll
    void init() {
        groupRepository.save(new ProductGroup());

        var bikeDto = ProductDTO.ProductDTOBuilder.aProductDTO()
                .withName("bike")
                .withDescription("bike")
                .withPrice(100)
                .build();
        bike = repository.save(mapper.fromDto(bikeDto));

        var carDto = ProductDTO.ProductDTOBuilder.aProductDTO()
                .withName("car")
                .withDescription("car")
                .withPrice(200)
                .build();
        car = repository.save(mapper.fromDto(carDto));
    }

    @AfterAll
    void cleanUp() { // to avoid constraint errors when droping database after tests
        repository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    void givenPriceRange_whenGettingListOfProducts_thenCorrect() {
        Product[] results = restTemplate.getForObject(URL_PREFIX + "price>50,price<150", Product[].class);

        assertThat(results)
                .isNotEmpty()
                .hasSize(1)
                .contains(bike)
                .doesNotContain(car);
    }

    @Test
    void givenMinPrice_whenGettingListOfProducts_thenCorrect() {
        Product[] results = restTemplate.getForObject(URL_PREFIX + "price>50", Product[].class);

        assertThat(results)
                .isNotEmpty()
                .hasSize(2)
                .contains(bike)
                .contains(car);
    }

}
