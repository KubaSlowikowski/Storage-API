package pl.slowikowski.demo.email;

import java.util.concurrent.Future;

public interface EmailService {
    Future<Void> sendMessage(Message message);

    Future<Void> sendMessageWithAttachment(Message message);
}
