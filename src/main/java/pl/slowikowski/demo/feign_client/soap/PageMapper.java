package pl.slowikowski.demo.feign_client.soap;

import com.raglis.library_api.soap._abstract.AbstractXmlType;
import com.raglis.library_api.soap._abstract.ObjectFactory;
import com.raglis.library_api.soap.pageables.OrderXml;
import com.raglis.library_api.soap.pageables.PageableXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import pl.slowikowski.demo.feign_client.crud.abstraction.AbstractLibraryDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.CommonWebMapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PageMapper<X extends AbstractXmlType, D extends AbstractLibraryDTO> {

    X toXml(D dto);
    D toDto(X xml);

    default PageableXml toPageableXmlFromPageable(Pageable pageable) {
        PageableXml pageableXml = new PageableXml();
        pageableXml.setSize(pageable.getPageSize());
        pageableXml.setPage(pageable.getPageNumber());
        pageableXml.getSortOrders().addAll(toResponseOrders(pageable.getSort().toList()));

        return pageableXml;
    }

    default PageImpl toPageFromPageXml(PageableXml pageable) {
        var content = new ArrayList<>();
        pageable.getContent().forEach(pageXml -> content.add(this.toDto(pageXml)));

        var result = new PageImpl<D>();
    }

    default PageableXml toPagedResponse(Page<X> page) {
        PageableXml pageableXml = new PageableXml();
        pageableXml.getContent().addAll(page.getContent());
        pageableXml.setPage(page.getNumber());
        pageableXml.setSize(page.getSize());
        pageableXml.getSortOrders().addAll(toResponseOrders(page.getSort().toList()));
        return pageableXml;
    }

    default List<Sort.Order> fromRequestOrders(List<OrderXml> orderXmlList) {
        List<Sort.Order> orders = new ArrayList<>(orderXmlList.size());
        orderXmlList.forEach(ox -> {
            if (ox.getSort() == null) return;
            if (ox.getDir() != null && ox.getDir().equalsIgnoreCase("DESC")) orders.add(Sort.Order.desc(ox.getSort()));
            else orders.add(Sort.Order.asc(ox.getSort()));
        });
        return orders;
    }

    List<OrderXml> toResponseOrders(List<Sort.Order> orderList);

    @Mapping(target = "sort", source = "property")
    @Mapping(target = "dir", source = "direction")
    OrderXml orderToOrderXml(Sort.Order order);
}
