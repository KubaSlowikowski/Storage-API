package pl.slowikowski.demo.feign_client.rest_client.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.AbstractLibraryController;

@RestController
@RequestMapping("${storage.library.books.url}")
class BookController extends AbstractLibraryController<BookDTO, BookClient> {
    public BookController(final BookClient client, BookClientService service) {
        super(client, service);
    }
}
