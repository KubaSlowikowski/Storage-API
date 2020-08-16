package pl.slowikowski.demo.feign_client.soap.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.crud.book.BookDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.AbstractSoapClientController;

@RestController
@RequestMapping("/library/api/soap/books")
class BookSoapController extends AbstractSoapClientController<BookDTO, BookSoapClient> {
    
    private final BookSoapClient client;

    BookSoapController(final BookSoapClient client) {
        super(client);
        this.client = client;
    }
}
