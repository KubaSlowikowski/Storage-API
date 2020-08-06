//package pl.slowikowski.demo.security.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import pl.slowikowski.demo.security.entity.User;
//import pl.slowikowski.demo.security.services.UserServiceImpl;
//
//@RestController
//@RequestMapping("/api/users")
//@PreAuthorize("hasRole('ADMIN')")
//public class UserController {
//    private final UserServiceImpl service;
//
//    public UserController(final UserServiceImpl service) {
//        this.service = service;
//    }
//
//    @GetMapping(path = "/{id}")
//    User findById(@PathVariable("id") Long id) {
//        return service.findById(id);
//    }
//}
