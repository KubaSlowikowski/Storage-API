package pl.slowikowski.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.demo.email.EmailServiceImpl;

@RestController
class Test {
    private final EmailServiceImpl emailService;

    Test(final EmailServiceImpl emailService, final ProductService service) {
        this.emailService = emailService;
    }

    @GetMapping(path = "/email")
    public void sendTestMessage() {
        emailService.sendSimpleMessage("sekenuw@poczta.fm", "testSubject", "123123123testsetsetest");
    }

    @GetMapping(path = "/attachment")
    public void sendAttachmentMessage() {
        emailService.sendMessageWithAttachment("sekenuw@poczta.fm", "testSubject", "123123123testsetsetest");
    }
}
