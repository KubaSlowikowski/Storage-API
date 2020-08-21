package pl.slowikowski.demo.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.crud.product.ProductService;

@RestController
class EmailController {
    private final EmailServiceImpl emailService;

    EmailController(final EmailServiceImpl emailService, final ProductService service) {
        this.emailService = emailService;
    }

    @GetMapping(path = "/email")
    public void sendTestMessage(@ModelAttribute Message message) {
        emailService.sendSimpleMessage(message);
    }

    @GetMapping(path = "/attachment")
    public void sendAttachmentMessage(@ModelAttribute Message message) {
        emailService.sendMessageWithAttachment(message);
    }
}
