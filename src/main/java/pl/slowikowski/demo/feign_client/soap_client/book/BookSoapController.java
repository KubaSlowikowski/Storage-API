package pl.slowikowski.demo.feign_client.soap_client.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.soap_client.abstraction.AbstractSoapClientController;

@RestController
@RequestMapping("${storage.library.books.soap.url}")
class BookSoapController extends AbstractSoapClientController<BookDTO, BookSoapClient> {

    BookSoapController(final BookSoapClient client) {
        super(client);
    }
}
