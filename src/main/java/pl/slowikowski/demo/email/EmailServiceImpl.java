package pl.slowikowski.demo.email;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.export.ExportDto;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final String NOREPLY_ADDRESS = "ttpraktyki2020storage@gmail.com";
    private final JavaMailSender emailSender;

    public EmailServiceImpl(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    @Async
    public CompletableFuture<Void> sendMessage(final Message message) {
        SimpleMailMessage toSend = new SimpleMailMessage();

        toSend.setFrom(message.getSendFrom());
        toSend.setTo(message.getSendTo());
        toSend.setSubject(message.getSubject());
        toSend.setText(message.getText());

        emailSender.send(toSend);
        logger.info("Email sent to address: " + message.getSendTo());
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    @SneakyThrows
    public CompletableFuture<Void> sendMessageWithAttachment(final Message message) {
        MimeMessage toSend = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(toSend, true); // pass 'true' to the constructor to create a multipart message
        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(message.getSendTo());
        helper.setSubject(message.getSubject());
        helper.setText(message.getText());
        ExportDto file = message.getFile();
        helper.addAttachment(file.getFileName() + file.getExtension(), new ByteArrayResource(file.getByteArray()));

        emailSender.send(toSend);
        logger.info("Email sent to: " + message.getSendTo());
        return CompletableFuture.completedFuture(null);
    }
}
