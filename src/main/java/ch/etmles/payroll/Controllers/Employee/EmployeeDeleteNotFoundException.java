package ch.etmles.payroll.Controllers.Employee;

public class EmployeeDeleteNotFoundException extends RuntimeException {

    EmployeeDeleteNotFoundException(Long id) { super("Could not delete employee (not found): " + id); }
}
