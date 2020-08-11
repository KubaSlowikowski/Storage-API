package pl.slowikowski.demo.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(NotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GroupModifyingForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String groupModifyingForbiddenErrorHandler(GroupModifyingForbiddenException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String wrongIdExceptionHandler(WrongIdException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String roleNotFoundErrorHandler(RoleNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserParameterAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userParameterAlreadyTakenErrorHandler(UserParameterAlreadyInUseException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getLocalizedMessage().split("default message ")[2];
        return message.substring(1, message.length() - 3);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authenticationExceptionHandler(AuthenticationException e) {
        return "Authentication failed: " + e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String missingRequestHeaderExceptionHandler(MissingRequestHeaderException e) {
        return e.getMessage();
    }

//    @ResponseBody
//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String userNotFoundExceptionHandler(UserNotFoundException e) {
//        return e.getMessage();
//    }
}
