package pl.slowikowski.demo.soap.exception;

import org.apache.cxf.interceptor.Fault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SoapExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(Fault.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String faultExceptionHandler(Fault e) {
        return e.getMessage();
    } //FIXME

}
