package pl.slowikowski.demo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.security.payload.request.LoginRequest;
import pl.slowikowski.demo.security.payload.request.SignupRequest;
import pl.slowikowski.demo.security.payload.response.JwtResponse;
import pl.slowikowski.demo.security.payload.response.MessageResponse;
import pl.slowikowski.demo.security.services.AuthServiceImpl;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthServiceImpl service;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = service.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        MessageResponse response = service.registerUser(signUpRequest);
        return ResponseEntity.ok(response);
    }
}
