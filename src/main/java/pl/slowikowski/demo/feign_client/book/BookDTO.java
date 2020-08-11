package pl.slowikowski.demo.feign_client.book;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.feign_client.abstraction.AbstractLibraryDTO;
import pl.slowikowski.demo.feign_client.reader.ReaderDTO;

@Getter
@Setter
public class BookDTO extends AbstractLibraryDTO {
    private String author;
    private int publicationYear;
    private ReaderDTO reader;
    private String title;
}
