package pl.slowikowski.demo.feign_client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO extends AbstractLibraryDTO {
    private String author;
    private int publicationYear;
    private ReaderDTO reader;
    private String title;

    @Override
    public String toString() {
        return (author == null) ? "" : author + " '" + title + "'";
    }
}
