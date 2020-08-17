package pl.slowikowski.demo.feign_client.crud.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.crud.abstraction.AbstractLibraryController;

@RestController
@RequestMapping("/library/api/books")
class BookController extends AbstractLibraryController<BookDTO, BookClient> {
    private final BookClient client;

    public BookController(final BookClient client) {
        super(client);
        this.client = client;
    }
}
