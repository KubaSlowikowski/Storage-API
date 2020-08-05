package pl.slowikowski.demo.security.services;

import pl.slowikowski.demo.security.payload.request.LoginRequest;
import pl.slowikowski.demo.security.payload.request.SignupRequest;
import pl.slowikowski.demo.security.payload.response.JwtResponse;
import pl.slowikowski.demo.security.payload.response.MessageResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    MessageResponse registerUser(SignupRequest signUpRequest);
}
