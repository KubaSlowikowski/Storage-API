package pl.slowikowski.demo.crud.productGroup;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.crud.abstraction.AbstractController;
import pl.slowikowski.demo.crud.abstraction.CommonSearchSpecificationBuilder;
import pl.slowikowski.demo.crud.abstraction.SearchOperation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/groups")
public class ProductGroupController extends AbstractController<ProductGroupService, ProductGroupDTO> {
    @Autowired
    ProductGroupRepository repo;
    @Autowired
    ProductGroupMapper mapper;

    public ProductGroupController(final ProductGroupServiceImpl service) {
        super(service);
    }



    @GetMapping("/search")
    @ResponseBody
    public List<ProductGroupDTO> search(@RequestParam(value = "search") String search) {
        CommonSearchSpecificationBuilder<ProductGroup> builder = new CommonSearchSpecificationBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<ProductGroup> spec = builder.build();
        return mapper.toListDto(repo.findAll(spec));
    }
}
