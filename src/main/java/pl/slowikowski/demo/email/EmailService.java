package pl.slowikowski.demo.email;

public interface EmailService {
    void sendSimpleMessage(Message message);

    void sendMessageWithAttachment(Message message);
}
