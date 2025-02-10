package ch.etmles.payroll.Controllers.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class EmployeeDeleteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeDeleteNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String employeeError(RuntimeException ex) { return ex.getMessage(); }
}
