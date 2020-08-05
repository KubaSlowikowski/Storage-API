package pl.slowikowski.demo.soap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;
import pl.slowikowski.jakub.soap_example._abstract.AbstractXmlElement;
import pl.slowikowski.jakub.soap_example._abstract.AbstractXmlType;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.pageable.PageableXml;

import java.util.ArrayList;
import java.util.List;

//O - element
//W - complexType
public interface CommonWebMapper<D extends AbstractDto, O extends AbstractXmlElement, W extends AbstractXmlType> {
    O toWebObject(D d);
    D toDto(O o);
    W toWeb(D d);
    List<W> toWebList(List<D> d);

    default Pageable toPageFromPageXml(PageableXml pageable) {

        if (pageable.getDir() == null) {
            pageable.setDir("ASC");
        }
        if (pageable.getSort() == null) {
            pageable.setSort("id");
        }
        return PageRequest.of(pageable.getPage(), pageable.getSize(), Sort.by(Sort.Direction.valueOf(pageable.getDir().toUpperCase()), pageable.getSort()));
    }

    default GetAllResponse toGetAllResponse(Page<D> page) {
        List<W> content = new ArrayList<>();
        page.getContent().forEach(d -> content.add(this.toWeb(d)));

        GetAllResponse result = new GetAllResponse();
        result.getContent().addAll(content);
        result.setTotalPages(page.getTotalElements());
        result.setTotalElements(page.getTotalElements());
        result.setNumber(page.getNumber());
        result.setSize(page.getSize());
        result.setSorted(page.getSort().isSorted());
        return result;
    }
}
