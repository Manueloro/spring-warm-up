package ch.etmles.payroll.Controllers.Employee;

public class EmployeeAlreadyInDepartment extends RuntimeException {
    public EmployeeAlreadyInDepartment(Long id) {
        super("Employee with id " + id + " already in a department");
    }
}
