package pl.slowikowski.demo.product.searchSpecification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;
import pl.slowikowski.demo.crud.abstraction.SearchOperation;
import pl.slowikowski.demo.crud.product.Product;
import pl.slowikowski.demo.crud.product.ProductRepository;
import pl.slowikowski.demo.crud.product.ProductSearchSpecification;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;
import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.slowikowski.demo.utils.Utils.getProduct;
import static pl.slowikowski.demo.utils.Utils.getSecondProduct;

@SpringBootTest
//@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
class ProductSearchSpecificationE2ETest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductGroupRepository groupRepository;

    private static Product bike;
    private static Product toy;
    private static ProductGroup group;

    @BeforeEach
    void init() {
        group = new ProductGroup();
        group.setName("name");
        group.setDescription("desc");
        group = groupRepository.saveAndFlush(group);

        bike = getProduct();
        bike.setGroup(group);
        bike.setDescription("bike description");

        toy = getSecondProduct();
        toy.setGroup(group);
        toy.setDescription("toy description");

        bike = productRepository.saveAndFlush(bike);
        toy = productRepository.saveAndFlush(toy);
    }

    @AfterEach
    void cleanup() {
        productRepository.delete(bike);
        productRepository.delete(toy);
        groupRepository.delete(group);
    }
    @Test
    void givenDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("description", SearchOperation.CONTAINS, "iption"));
        List<Product> results = productRepository.findAll(spec);

        assertThat(results)
                .isNotEmpty()
                .contains(bike)
                .contains(toy)
                .hasSize(2);
    }

    @Test
    void givenNameAndDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("name", SearchOperation.EQUALITY, "Toy"));
        ProductSearchSpecification spec2 = new ProductSearchSpecification(new SearchCriteria("description", SearchOperation.EQUALITY, "toy description"));

        List<Product> results = productRepository.findAll(Specification.where(spec).and(spec2));

        assertThat(results)
                .isNotEmpty()
                .containsExactly(toy)
                .doesNotContain(bike)
                .hasSize(1);
    }

    @Test
    void givenDescriptionAndPriceRange_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("price", SearchOperation.GREATER_THAN, "15"));
        ProductSearchSpecification spec2 = new ProductSearchSpecification(new SearchCriteria("description", SearchOperation.ENDS_WITH, "description"));
        ProductSearchSpecification spec3 = new ProductSearchSpecification(new SearchCriteria("price", SearchOperation.LESS_THAN, "150"));

        List<Product> results = productRepository.findAll(Specification.where(spec).and(spec2).and(spec3));

        assertThat(results)
                .isNotEmpty()
                .containsExactly(toy)
                .doesNotContain(bike)
                .hasSize(1);
    }

    @Test
    void givenWrongNameAndDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("name", SearchOperation.LIKE, "kjfldshafiuyseuin"));
        ProductSearchSpecification spec2 = new ProductSearchSpecification(new SearchCriteria("description", SearchOperation.LIKE, "aieuswrydhi54fb"));

        List<Product> results = productRepository.findAll(Specification.where(spec).and(spec2));

        assertThat(results)
                .isNotNull()
                .doesNotContain(toy, bike);
    }

    @Test
    void givenPartialDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("description", SearchOperation.ENDS_WITH, "ion"));

        List<Product> results = productRepository.findAll(Specification.where(spec));

        assertThat(results)
                .isNotEmpty()
                .contains(toy)
                .contains(bike)
                .hasSize(2);
    }

    @Test
    void givenNameInverse_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("name", SearchOperation.NEGATION, "Toy"));
        List<Product> results = productRepository.findAll(Specification.where(spec));

        assertThat(results)
                .isNotEmpty()
                .containsExactly(bike)
                .doesNotContain(toy)
                .hasSize(1);
    }

    @Test
    void givenNamePrefix_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("name", SearchOperation.STARTS_WITH, "T"));

        List<Product> results = productRepository.findAll(Specification.where(spec));

        assertThat(results)
                .isNotEmpty()
                .containsExactly(toy)
                .doesNotContain(bike)
                .hasSize(1);
    }
}
