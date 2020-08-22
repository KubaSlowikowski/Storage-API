package pl.slowikowski.demo.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;

@ControllerAdvice
class EmailExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //FIXME
    public String messagingExceptionHandler(MessagingException e) {
        return e.getMessage();
    }
}
