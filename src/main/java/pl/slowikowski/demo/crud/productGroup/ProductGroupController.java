package pl.slowikowski.demo.crud.productGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.crud.abstraction.AbstractController;

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



    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public List<ProductGroupDTO> search(@RequestParam(value = "search") String search) {
        ProductGroupSearchSpecificationBuilder builder = new ProductGroupSearchSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS); //2nd arg - non english characters
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<ProductGroup> spec = builder.build();
        return mapper.toListDto(repo.findAll(spec));
    }
}
