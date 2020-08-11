package pl.slowikowski.demo.feign_client.crud.reader;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.feign_client.crud.abstraction.AbstractLibraryDTO;

import java.time.LocalDate;

@Getter
@Setter
public class ReaderDTO extends AbstractLibraryDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private String role;
    private String username;
    private String password;
}
