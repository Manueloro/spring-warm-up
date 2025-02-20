package ch.etmles.payroll.Controllers.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

class RessourceIDNotFound extends RuntimeException {
    public RessourceIDNotFound(Long id, String ressourceName) {
        super("Could not find " + ressourceName + " " + id);
    }
}

class RessourceDeleteNotFound extends RuntimeException {
    public RessourceDeleteNotFound(Long id, String ressourceName) {
        super("Could not delete " + ressourceName + " (not found): " + id);
    }
}

@ControllerAdvice
class RessourceNotFoundAdvice {

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