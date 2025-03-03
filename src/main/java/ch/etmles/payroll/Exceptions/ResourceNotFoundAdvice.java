package ch.etmles.payroll.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceIDNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String IDNotFound(ResourceIDNotFound ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ResourceDeleteNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String deleteNotFound(ResourceDeleteNotFound ex){
        return ex.getMessage();
    }
}