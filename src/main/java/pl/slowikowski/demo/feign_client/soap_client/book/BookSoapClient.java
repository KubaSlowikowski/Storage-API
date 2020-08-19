package pl.slowikowski.demo.feign_client.soap_client.book;

import com.raglis.library_api.soap.books.BookPortType;
import com.raglis.library_api.soap.books.BookResponse;
import com.raglis.library_api.soap.books.GetAllBooksResponse;
import com.raglis.library_api.soap.books.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.crud.exception.WrongIdException;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.soap_client.abstraction.CommonSoapClient;

@Component
public class BookSoapClient implements CommonSoapClient<BookDTO> {

    private final BookPortType proxy;
    private final ObjectFactory objectFactory;
    private final BookWebMapper mapper;

    public BookSoapClient(final BookPortType proxy, final ObjectFactory objectFactory, final BookWebMapper mapper) {
        this.proxy = proxy;
        this.objectFactory = objectFactory;
        this.mapper = mapper;
    }

    @Override
    public Page<BookDTO> findAll(final Pageable pageable, final String search) {
        var request = objectFactory.createGetAllBooksRequest();
        request.setSearch(search);
        request.setPageable(mapper.toPageableXml(pageable));

        GetAllBooksResponse response = proxy.getAll(request);

        return mapper.toPageImpl(response, pageable);
    }

    @Override
    public BookDTO getById(final long id) {
        var request = objectFactory.createGetBookRequest();
        request.setId(id);
        BookResponse response = proxy.getById(request);
        return mapper.toDto(response.getBook());
    }

    @Override
    public BookDTO save(final BookDTO dto) {
        var request = mapper.toCreateBookRequestFromDto(dto);
        BookResponse response = proxy.create(request);
        return mapper.toDto(response.getBook());
    }

    @Override
    public BookDTO update(final BookDTO dto, final long id) {
        if (dto.getId() != id) {
            throw new WrongIdException();
        }
        var request = objectFactory.createUpdateBookRequest();
        request.setBook(mapper.toXml(dto));
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

