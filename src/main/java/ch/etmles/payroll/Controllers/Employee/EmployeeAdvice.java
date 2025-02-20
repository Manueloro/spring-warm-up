package ch.etmles.payroll.Controllers.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyInDepartment.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeAlreadyInADepartment(EmployeeAlreadyInDepartment ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotInDepartment.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeNotInADepartment(EmployeeNotInDepartment ex){
        return ex.getMessage();
    }
}
