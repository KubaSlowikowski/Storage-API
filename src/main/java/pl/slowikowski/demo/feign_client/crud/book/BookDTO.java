package pl.slowikowski.demo.feign_client.crud.book;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.feign_client.crud.abstraction.AbstractLibraryDTO;
import pl.slowikowski.demo.feign_client.crud.reader.ReaderDTO;

@Getter
@Setter
public class BookDTO extends AbstractLibraryDTO {
    private String author;
    private int publicationYear;
    private ReaderDTO reader;
    private String title;
}
