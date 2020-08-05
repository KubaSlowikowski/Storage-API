//package pl.slowikowski.demo.productGroup.searchSpecification;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.ActiveProfiles;
//import pl.slowikowski.demo.crud.productGroup.ProductGroup;
//import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
//import pl.slowikowski.demo.crud.productGroup.ProductGroupMapper;
//import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
////@RunWith(SpringRunner.class)
//@ActiveProfiles("testing")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //aby @BeforeAll nie musialo byc static
////@WithMockUser(username = "admin", roles = { "ADMIN" })
//class ProductGroupControllerSearchSpecificationE2ETest {
//    @Autowired
//    ProductGroupRepository repository;
//
//    @Autowired
//    ProductGroupMapper groupMapper = ProductGroupMapper.INSTANCE;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private ProductGroup sport;
//    private ProductGroup transport;
//
//    private final String URL_PREFIX = "http://localhost:8080/api/groups/search?search=";
//
//    @BeforeAll
//    void init() {
//            var sportDto = ProductGroupDTO.ProductGroupDTOBuilder.aProductGroupDTO()
//                    .withName("sport")
//                    .withDescription("sport")
//                    .build();
//            sport = repository.save(groupMapper.fromDto(sportDto));
//
//            var transportDto = ProductGroupDTO.ProductGroupDTOBuilder.aProductGroupDTO()
//                    .withName("transport")
//                    .withDescription("transport")
//                    .build();
//            transport = repository.save(groupMapper.fromDto(transportDto));
//    }
//
//    @AfterAll
//    void cleanUp() {
//        repository.deleteAll();
//    }
//
//    @Test
//    void givenNameAndDescription_whenGettingListOfProductGroups_thenCorrect() {
//        ProductGroup[] results = restTemplate.getForObject(URL_PREFIX + "name:sport,description:sport", ProductGroup[].class);
//
//        assertThat(results)
//                .isNotEmpty()
//                .hasSize(1)
//                .contains(sport)
//                .doesNotContain(transport);
//    }
//
//    @Test
//    void givenNameInverse_whenGettingListOfProductGroups_thenCorrect() {
//        ProductGroup[] results = restTemplate.getForObject(URL_PREFIX + "name!sport", ProductGroup[].class);
//
//        assertThat(results)
//                .isNotEmpty()
//                .hasSize(1)
//                .contains(transport)
//                .doesNotContain(sport);
//    }
//
//    // MIN prefix test ang RANGE prefix
//
//    @Test
//    void givenNamePrefix_whenGettingListOfProductGroups_thenCorrect() {
//        ProductGroup[] results = restTemplate.getForObject(URL_PREFIX + "name:tran*", ProductGroup[].class);
//
//        assertThat(results)
//                .isNotEmpty()
//                .hasSize(1)
//                .contains(transport)
//                .doesNotContain(sport);
//    }
//
//    @Test
//    void givenNameSuffix_whenGettingListOfProductGroups_thenCorrect() {
//        ProductGroup[] results = restTemplate.getForObject(URL_PREFIX + "name:*ort", ProductGroup[].class);
//
//        assertThat(results)
//                .isNotEmpty()
//                .hasSize(2)
//                .contains(transport)
//                .contains(sport);
//    }
//
//    @Test
//    void givenNameSubstring_whenGettingListProductGroups_thenCorrect() {
//        ProductGroup[] results = restTemplate.getForObject(URL_PREFIX + "name:*or*", ProductGroup[].class);
//
//        assertThat(results)
//                .isNotEmpty()
//                .hasSize(2)
//                .contains(transport)
//                .contains(sport);
//    }
//}
