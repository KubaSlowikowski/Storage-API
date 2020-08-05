package pl.slowikowski.demo.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.exception.RoleNotFoundException;
import pl.slowikowski.demo.crud.exception.UserParameterAlreadyInUseException;
import pl.slowikowski.demo.security.Roles;
import pl.slowikowski.demo.security.entity.Role;
import pl.slowikowski.demo.security.entity.User;
import pl.slowikowski.demo.security.jwt.JwtUtils;
import pl.slowikowski.demo.security.payload.request.LoginRequest;
import pl.slowikowski.demo.security.payload.request.SignupRequest;
import pl.slowikowski.demo.security.payload.response.JwtResponse;
import pl.slowikowski.demo.security.payload.response.MessageResponse;
import pl.slowikowski.demo.security.repository.RoleRepository;
import pl.slowikowski.demo.security.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    public AuthServiceImpl(final AuthenticationManager authenticationManager, final UserRepository userRepository, final RoleRepository roleRepository, final PasswordEncoder encoder, final JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserParameterAlreadyInUseException("Username");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserParameterAlreadyInUseException("E-mail");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> requestRoles = signUpRequest.getRole();
        Set<Role> resultRoles = new HashSet<>();

        if (requestRoles == null || requestRoles.isEmpty()) {
            throw new RoleNotFoundException();
        }

        requestRoles.forEach(role -> {
            String prefix = "ROLE_" + role.toUpperCase();
            if (!Roles.contains(prefix)) throw new RoleNotFoundException(prefix);

            Role adminRole = roleRepository.findByName(Roles.valueOf(prefix))
                    .orElseThrow(() -> new RoleNotFoundException(prefix));
            if (!resultRoles.contains(adminRole)) {
                resultRoles.add(adminRole);
            }
        });
        user.setRoles(resultRoles);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }
}
