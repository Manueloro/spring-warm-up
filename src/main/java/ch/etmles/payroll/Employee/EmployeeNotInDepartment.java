package ch.etmles.payroll.Employee;

public class EmployeeNotInDepartment extends RuntimeException {
    public EmployeeNotInDepartment(Long id) {
        super("Employee with id " + id + " not in a department");
    }
}
