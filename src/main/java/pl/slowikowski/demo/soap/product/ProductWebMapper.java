package pl.slowikowski.demo.soap.product;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.soap.CommonWebMapper;
import pl.slowikowski.jakub.soap_example.product.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductWebMapper extends CommonWebMapper<ProductDTO, ProductObject, Product> {
    ProductDTO toDtoFromSaveRequest(SaveProductRequest saveRequest);

    default Pageable toPageFromGetAllRequest(GetAllProductsRequest request) {
        var pageable = request.getPageable();

        if (pageable.getDir() == null) {
            pageable.setDir("ASC");
        }
        if (pageable.getSort() == null) {
            pageable.setSort("id");
        }
        return PageRequest.of(pageable.getPage(), pageable.getSize(), Sort.by(Sort.Direction.valueOf(pageable.getDir().toUpperCase()), pageable.getSort()));
    }

    default GetAllProductsResponse toGetAllProductsResponse(Page<ProductDTO> page) {
        List<Product> content = new ArrayList<>();
        page.getContent().forEach(productDTO -> content.add(this.toWeb(productDTO)));
        GetAllProductsResponse result = new GetAllProductsResponse();
        result.getContent().addAll(content);
        result.setTotalPages(page.getTotalElements());
        result.setTotalElements(page.getTotalElements());
        result.setNumber(page.getNumber());
        result.setSize(page.getSize());
        result.setSorted(page.getSort().isSorted());
        return result;
    }
}
