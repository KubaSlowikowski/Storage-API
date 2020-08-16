package pl.slowikowski.demo.feign_client.soap.book;

import com.raglis.library_api.soap.books.BookXml;
import com.raglis.library_api.soap.books.CreateBookRequest;
import com.raglis.library_api.soap.books.UpdateBookRequest;
import org.mapstruct.Mapper;
import pl.slowikowski.demo.feign_client.crud.book.BookDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.CommonLibrarySoapMapper;

@Mapper(componentModel = "spring")
interface BookWebMapper extends CommonLibrarySoapMapper<BookXml, BookDTO> {
    CreateBookRequest toCreateBookRequestFromDto(BookDTO dto);

    UpdateBookRequest toUpdateBookRequestFromDto(BookDTO dto);
}
