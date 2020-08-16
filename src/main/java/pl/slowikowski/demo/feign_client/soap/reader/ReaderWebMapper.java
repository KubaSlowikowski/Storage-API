package pl.slowikowski.demo.feign_client.soap.reader;

import com.raglis.library_api.soap.readers.CreateReaderRequest;
import com.raglis.library_api.soap.readers.ReaderXml;
import com.raglis.library_api.soap.readers.UpdateReaderRequest;
import org.mapstruct.Mapper;
import pl.slowikowski.demo.feign_client.crud.reader.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.CommonLibrarySoapMapper;

@Mapper(componentModel = "spring")
interface ReaderWebMapper extends CommonLibrarySoapMapper<ReaderXml, ReaderDTO> {
    CreateReaderRequest toCreateReaderRequestFromDto(ReaderDTO dto);

    UpdateReaderRequest toUpdateReaderRequestFromDto(ReaderDTO dto);
//
//    @Override
//    default PageImpl toPageFromPageXml(PageableXml pageable) {
//        var content = new ArrayList<>();
//        pageable.getContent().forEach(xml -> content.add(this.toDto(xml)));
//
//        var result = new PageImpl<D>();
//
//        return null;
//    }
}
