package pl.slowikowski.demo.feign_client.dto;

import lombok.Getter;
import lombok.Setter;

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

    @Override
    public String toString() {
        return firstName + " " + lastName + " (id=" + getId() + ")";
    }
}
