package pl.slowikowski.demo.feign_client.dto;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.export.pdf.annotation.PdfIgnoreField;
import pl.slowikowski.demo.export.pdf.annotation.PdfTableName;

import java.time.LocalDate;

@Getter
@Setter
@PdfTableName(value = "Readers list")
public class ReaderDTO extends AbstractLibraryDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private String role;
    private String username;
    @PdfIgnoreField
    private String password;

    @Override
    public String toString() {
        return firstName + " " + lastName + " (id=" + getId() + ")";
    }
}
