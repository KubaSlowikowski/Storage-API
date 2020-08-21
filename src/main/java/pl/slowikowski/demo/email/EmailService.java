package pl.slowikowski.demo.email;

public interface EmailService {
    void sendMessage(Message message);

    void sendMessageWithAttachment(Message message);
}
