package pl.slowikowski.demo.feign_client.soap_client.reader;

import com.raglis.library_api.soap.readers.CreateReaderRequest;
import com.raglis.library_api.soap.readers.ReaderXml;
import com.raglis.library_api.soap.readers.UpdateReaderRequest;
import org.mapstruct.Mapper;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap_client.abstraction.CommonLibrarySoapMapper;

@Mapper(componentModel = "spring")
interface ReaderWebMapper extends CommonLibrarySoapMapper<ReaderXml, ReaderDTO> {
    CreateReaderRequest toCreateReaderRequestFromDto(ReaderDTO dto);

    UpdateReaderRequest toUpdateReaderRequestFromDto(ReaderDTO dto);
}
