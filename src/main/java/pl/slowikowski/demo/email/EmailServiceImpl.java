package pl.slowikowski.demo.email;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.demo.export.ExportDto;

@Component
public class EmailServiceImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "ttpraktyki2020storage@gmail.com";
    private JavaMailSender emailSender;
    private final ProductService service;

    public EmailServiceImpl(final JavaMailSender emailSender, final ProductService service) {
        this.emailSender = emailSender;
        this.service = service;
    }


    @Override
    public void sendSimpleMessage(final String to, final String subject, final String text) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(NOREPLY_ADDRESS);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    @Override
    @SneakyThrows
    public void sendMessageWithAttachment(final String to, final String subject, final String text) {
        MimeMessage message = emailSender.createMimeMessage();
        // pass 'true' to the constructor to create a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        ExportDto pdfReport = service.getPdfReport(Pageable.unpaged(), null);

        ByteArrayResource byteArrayResource = new ByteArrayResource(pdfReport.getByteArray());

        helper.addAttachment(pdfReport.getFileName() + pdfReport.getExtension(), byteArrayResource);

        emailSender.send(message);
    }

    @Override
    @SneakyThrows
    public void sendHtmlMessage(final String to, final String subject, final String htmlBody) {
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//        helper.setFrom(NOREPLY_ADDRESS);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(htmlBody, true);
//        helper.addInline("attachment.png", resourceFile);
//        emailSender.send(message);
    }
}
