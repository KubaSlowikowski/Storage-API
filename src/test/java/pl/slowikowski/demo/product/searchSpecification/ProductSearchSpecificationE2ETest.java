package pl.slowikowski.demo.product.searchSpecification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.slowikowski.demo.crud.abstraction.SearchCriteria;
import pl.slowikowski.demo.crud.product.Product;
import pl.slowikowski.demo.crud.product.ProductRepository;
import pl.slowikowski.demo.crud.product.ProductSearchSpecification;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;
import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.slowikowski.demo.utils.Utils.getProduct;
import static pl.slowikowski.demo.utils.Utils.getSecondProduct;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
class ProductSearchSpecificationE2ETest {

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private  ProductGroupRepository groupRepository;

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
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("description", ":", "iption"));

        List<Product> results = productRepository.findAll(spec);

        assertThat(results)
                .isNotEmpty()
                .contains(bike)
                .contains(toy);
    }

    @Test
    void givenNameAndDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("name", ":", "Toy"));
        ProductSearchSpecification spec2 = new ProductSearchSpecification(new SearchCriteria("description", ":", "toy description"));

        List<Product> results = productRepository.findAll(Specification.where(spec).and(spec2));

        assertThat(results)
                .isNotEmpty()
                .contains(toy)
                .doesNotContain(bike);
    }

    @Test
    void givenDescriptionAndPrice_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("price", ">", "15"));
        ProductSearchSpecification spec2 = new ProductSearchSpecification(new SearchCriteria("description", ":", "description"));

        List<Product> results = productRepository.findAll(Specification.where(spec).and(spec2));

        assertThat(results)
                .isNotEmpty()
                .contains(toy)
                .doesNotContain(bike);
    }

    @Test
    void givenWrongNameAndDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("name", ":", "kjfldshafiuyseuin"));
        ProductSearchSpecification spec2 = new ProductSearchSpecification(new SearchCriteria("description", ":", "aieuswrydhi54fb"));

        List<Product> results = productRepository.findAll(Specification.where(spec).and(spec2));

        assertThat(results)
                .isNotNull()
                .doesNotContain(toy)
                .doesNotContain(bike);
    }

    @Test
    void givenPartialDescription_whenGettingListOfProducts_thenCorrect() {
        ProductSearchSpecification spec = new ProductSearchSpecification(new SearchCriteria("description", ":", "ion"));

        List<Product> results = productRepository.findAll(Specification.where(spec));

        assertThat(results)
                .isNotEmpty()
                .contains(toy)
                .contains(bike);
    }
}
