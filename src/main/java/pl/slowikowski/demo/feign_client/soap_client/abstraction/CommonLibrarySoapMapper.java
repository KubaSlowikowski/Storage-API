package pl.slowikowski.demo.feign_client.soap_client.abstraction;

import com.raglis.library_api.soap._abstract.AbstractXmlType;
import com.raglis.library_api.soap._abstract.GetAllResponse;
import com.raglis.library_api.soap.pageables.OrderXml;
import com.raglis.library_api.soap.pageables.PageableXml;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.AbstractLibraryDTO;

import java.util.ArrayList;
import java.util.List;

public interface CommonLibrarySoapMapper<X extends AbstractXmlType, D extends AbstractLibraryDTO> {

    X toXml(D dto);

    D toDto(X xml);

    default PageableXml toPageableXml(Pageable pageable) {
        PageableXml pageableXml = new PageableXml();
        pageableXml.setSize(pageable.getPageSize());
        pageableXml.setPage(pageable.getPageNumber());
        pageableXml.getSortOrders().addAll(toResponseOrders(pageable.getSort().toList()));
        return pageableXml;
    }

    List<OrderXml> toResponseOrders(List<Sort.Order> orderList);

    default PageImpl<D> toPageImpl(GetAllResponse response, Pageable pageable) {
        var content = new ArrayList<D>();
        response.getPage().getContent().forEach(xml -> content.add(this.toDto((X) xml)));
        return new PageImpl<>(content, pageable, content.size());
    }
}
