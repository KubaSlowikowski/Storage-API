package pl.slowikowski.demo.feign_client.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.soap.SOAPFaultException;
import java.net.SocketTimeoutException;

@ControllerAdvice
public class LibraryExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String feignNotFoundExceptionHandler(FeignException e) {
        String message = e.getMessage().split("\\)]: \\[")[1];
        return message.substring(0, message.length() - 1);
    }

    @ResponseBody
    @ExceptionHandler(FeignException.Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String feignConflictExceptionHandler(FeignException e) {
        String message = e.getMessage().split("\\)]: \\[")[1];
        return message.substring(0, message.length() - 1);
    }

    @ResponseBody
    @ExceptionHandler(FeignException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String feignBadRequestExceptionHandler(FeignException e) {
        String message = e.getMessage().split("\\)]: \\[")[1];
        return message.substring(0, message.length() - 1);
    }

    @ResponseBody
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String feignMethodNotAllowedExceptionHandler(FeignException e) {
        String message = e.getMessage().split("\\)]: \\[")[1];
        return message.substring(0, message.length() - 1);
    }

    @ResponseBody
    @ExceptionHandler(FeignException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String feignUnauthorizedExceptionHandler(FeignException e) {
        return e.status() + " Unauthorized to library.";
    }

    @ResponseBody
    @ExceptionHandler(SOAPFaultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String soapFaultExceptionHandler(SOAPFaultException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public String socketTimeoutExceptionHandler(SocketTimeoutException e ) {
        return e.getMessage();
    }
}
