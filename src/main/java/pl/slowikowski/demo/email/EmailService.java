package pl.slowikowski.demo.email;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);

    public void sendMessageWithAttachment(String to, String subject, String text);

    public void sendHtmlMessage(String to, String subject, String htmlBody);
}
