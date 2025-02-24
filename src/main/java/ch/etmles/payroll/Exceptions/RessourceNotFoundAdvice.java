package ch.etmles.payroll.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RessourceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RessourceIDNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String IDNotFound(RessourceIDNotFound ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RessourceDeleteNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String deleteNotFound(RessourceDeleteNotFound ex){
        return ex.getMessage();
    }
}