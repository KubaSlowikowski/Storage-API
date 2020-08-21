package pl.slowikowski.demo.email;

import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.export.ExportDto;

import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "ttpraktyki2020storage@gmail.com";
    private final JavaMailSender emailSender;

    public EmailServiceImpl(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(final Message message) {
        SimpleMailMessage toSend = new SimpleMailMessage();

        toSend.setFrom(message.getSendFrom());
        toSend.setTo(message.getSendTo());
        toSend.setSubject(message.getSubject());
        toSend.setText(message.getText());

        emailSender.send(toSend);
    }

    @Override
    @SneakyThrows
    public void sendMessageWithAttachment(final Message message) {
        MimeMessage toSend = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(toSend, true); // pass 'true' to the constructor to create a multipart message
        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(message.getSendTo());
        helper.setSubject(message.getSubject());
        helper.setText(message.getText());
        ExportDto file = message.getFile();
        helper.addAttachment(file.getFileName() + file.getExtension(), new ByteArrayResource(file.getByteArray()));

        emailSender.send(toSend);
    }
}
