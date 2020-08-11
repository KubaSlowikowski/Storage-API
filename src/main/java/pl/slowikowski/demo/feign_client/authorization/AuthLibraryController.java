package pl.slowikowski.demo.feign_client.authorization;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/auth")
class AuthLibraryController {
    private final AuthLibraryClient client;

    public AuthLibraryController(final AuthLibraryClient client) {
        this.client = client;
    }

    @PostMapping
    JwtResponse authenticateUser(@RequestBody AuthLibraryRequest request) {
        return client.authenticateUser(request);
    }
}
