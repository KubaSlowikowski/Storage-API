package pl.slowikowski.demo.feign_client.authorization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLibraryRequest {
    private String username;
    private String password;
}
