package pl.slowikowski.demo.crud.product;

import com.google.common.base.Joiner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.crud.abstraction.AbstractController;
import pl.slowikowski.demo.crud.abstraction.SearchOperation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductService, ProductDTO> {

    private final ProductService service;
    private final ProductMapper mapper;
    private final ProductRepository repo;

    public ProductController(final ProductService service, ProductMapper mapper, ProductRepository repo) {
        super(service);
        this.service = service;
        this.mapper = mapper;
        this.repo = repo;
    }

    @GetMapping(path = "/all/{id}")
    List<ProductDTO> findAllProductsByGroupId(@PathVariable("id") Long groupId) {
        return service.findAllByGroupId(groupId);
    }

    @PostMapping(path = "/{id}")
    ProductDTO buyProduct(@PathVariable Long id) {
        return service.buyProduct(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<ProductDTO> search(@RequestParam(value = "search") String search) {
        Specification<Product> spec = resolveSpecification(search);
        return mapper.toListDto(repo.findAll(spec));
    }

    private Specification<Product> resolveSpecification(String searchParameters) {
        ProductSearchSpecificationBuilder builder = new ProductSearchSpecificationBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\p{Punct}?)(\\w+?)("
                        + operationSetExper
                        + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchParameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3),
                    matcher.group(5), matcher.group(4), matcher.group(6));
        }

        return builder.build();
    }
}
