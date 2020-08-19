package pl.slowikowski.demo.feign_client.authorization;

import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.feign_client.dto.AbstractLibraryDTO;

@Getter
@Setter
public class JwtResponse extends AbstractLibraryDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String type;
    private String[] roles;
    private String token;
}
