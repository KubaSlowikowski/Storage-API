//package pl.slowikowski.demo.productGroup.searchSpecification;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.ActiveProfiles;
//import pl.slowikowski.demo.crud.productGroup.ProductGroup;
//import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
////@RunWith(SpringRunner.class)
//@ActiveProfiles("testing")
////@WithMockUser(username = "admin", roles = { "ADMIN" })
//class ProductGroupControllerSearchSpecificationTest {
//    @Autowired
//    ProductGroupRepository repository;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private ProductGroup sport;
//    private ProductGroup transport;
//
//    private final String URL_PREFIX = "http://localhost:8080/groups/search?search=";
//
//    @BeforeEach
//    void init() {
//        sport = ProductGroup.ProductGroupBuilder.aProductGroup()
//                .withName("sport")
//                .withDescription("sport")
//                .build();
//        repository.save(sport);
//
//        transport = ProductGroup.ProductGroupBuilder.aProductGroup()
//                .withName("transport")
//                .withDescription("transport")
//                .build();
//        repository.save(transport);
//    }
//
//    @Test
//    void givenNameAndDescription_whenGettingListOfProductGroups_thenCorrect() {
//        ProductGroup[] results = restTemplate.getForObject(URL_PREFIX + "name:sport,description:sport", ProductGroup[].class);
//
//        assertThat(results)
//                .isNotEmpty()
//                .containsExactly(sport)
//                .doesNotContain(transport);
//    }
//}
