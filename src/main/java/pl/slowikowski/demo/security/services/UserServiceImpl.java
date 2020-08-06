package pl.slowikowski.demo.security.services;

import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.exception.NotFoundException;
import pl.slowikowski.demo.security.entity.User;
import pl.slowikowski.demo.security.repository.UserRepository;

@Service
public class UserServiceImpl {
    private final UserRepository repository;

    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) {
        var result = repository.findById(id).orElseThrow(() -> new NotFoundException(id, User.class.getSimpleName()));

        return result;
    }
}
