package pl.slowikowski.demo.feign_client.soap.abstraction;

import com.raglis.library_api.soap._abstract.AbstractXmlType;
import org.mapstruct.Mapper;
import pl.slowikowski.demo.feign_client.crud.abstraction.AbstractLibraryDTO;

@Mapper(componentModel = "spring")
public interface CommonWebMapper<X extends AbstractXmlType, D extends AbstractLibraryDTO> {
    X toXml(D dto);
    D toDto(X xml);
}
