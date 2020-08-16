package pl.slowikowski.demo.feign_client.soap.book;

import com.raglis.library_api.soap.books.BookPortType;
import com.raglis.library_api.soap.books.BookResponse;
import com.raglis.library_api.soap.books.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.crud.exception.WrongIdException;
import pl.slowikowski.demo.feign_client.crud.book.BookDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.CommonSoapClient;

@Component
class BookSoapClient implements CommonSoapClient<BookDTO> {

    private final BookPortType proxy;
    private final ObjectFactory objectFactory;
    private final BookWebMapper mapper;

    BookSoapClient(final BookPortType proxy, final ObjectFactory objectFactory, final BookWebMapper mapper) {
        this.proxy = proxy;
        this.objectFactory = objectFactory;
        this.mapper = mapper;
    }

    @Override
    public Page<BookDTO> findAll(final Pageable pageable, final String search) {
        return null;
    }

    @Override
    public BookDTO getById(final long id) {
        var request = objectFactory.createGetBookRequest();
        request.setId(id);
        BookResponse response = proxy.getById(request);
        return mapper.toDto(response.getBook());
    }

    @Override
    public BookDTO add(final BookDTO dto) {
        var request = mapper.toCreateBookRequestFromDto(dto);
        BookResponse response = proxy.create(request);
        return mapper.toDto(response.getBook());
    }

    @Override
    public BookDTO update(final BookDTO dto, final long id) {
        if (dto.getId() != id) {
            throw new WrongIdException();
        }
        var request = mapper.toUpdateBookRequestFromDto(dto);
        BookResponse response = proxy.update(request);
        return mapper.toDto(response.getBook());
    }

    @Override
    public BookDTO delete(final long id) {
        var request = objectFactory.createDeleteBookRequest();
        request.setId(id);
        ;
        BookResponse response = proxy.delete(request);
        return mapper.toDto(response.getBook());
    }
}
