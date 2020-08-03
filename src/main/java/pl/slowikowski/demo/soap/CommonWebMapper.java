package pl.slowikowski.demo.soap;

import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import java.util.List;
import java.util.Set;

public interface CommonWebMapper<D extends AbstractDto, W> {
    W fromDto(D d);
    Set<W> fromSetDto(Set<D> d);
    List<W> fromListDto(List<D> d);
}
