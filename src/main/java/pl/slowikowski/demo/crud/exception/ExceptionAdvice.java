package pl.slowikowski.demo.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
