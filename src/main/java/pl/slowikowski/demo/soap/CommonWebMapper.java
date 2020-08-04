package pl.slowikowski.demo.soap;

import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import java.util.List;

//O - element
//W - complexType
public interface CommonWebMapper<D extends AbstractDto, O, W> {
    O toWebObject(D d);
    D toDto(O o);
    List<W> toWebList(List<D> d);
}
