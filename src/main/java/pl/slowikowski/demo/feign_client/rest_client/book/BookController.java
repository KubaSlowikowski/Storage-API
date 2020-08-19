package pl.slowikowski.demo.feign_client.rest_client.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.AbstractLibraryController;

@RestController
@RequestMapping("/library/api/books")
class BookController extends AbstractLibraryController<BookDTO, BookClient> {
    private final BookClient client;

    public BookController(final BookClient client) {
        super(client);
        this.client = client;
    }
}
