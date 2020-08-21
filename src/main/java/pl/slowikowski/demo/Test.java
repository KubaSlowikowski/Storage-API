package pl.slowikowski.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.email.EmailServiceImpl;

@RestController
class Test {
    private final EmailServiceImpl emailService;

    Test(final EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping(path = "/email")
    public void sendTestMessage() {
        emailService.sendSimpleMessage("sekenuw@gmail.com", "testSubject", "123123123testsetsetest");
    }
}
