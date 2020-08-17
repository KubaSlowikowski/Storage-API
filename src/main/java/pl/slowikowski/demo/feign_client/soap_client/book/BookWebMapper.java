package pl.slowikowski.demo.feign_client.soap_client.book;

import com.raglis.library_api.soap.books.BookXml;
import com.raglis.library_api.soap.books.CreateBookRequest;
import org.mapstruct.Mapper;
import pl.slowikowski.demo.feign_client.crud.book.BookDTO;
import pl.slowikowski.demo.feign_client.soap_client.abstraction.CommonLibrarySoapMapper;

@Mapper(componentModel = "spring")
interface BookWebMapper extends CommonLibrarySoapMapper<BookXml, BookDTO> {
    CreateBookRequest toCreateBookRequestFromDto(BookDTO dto);
}
