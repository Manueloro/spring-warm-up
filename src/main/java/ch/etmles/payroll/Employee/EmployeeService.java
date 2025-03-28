package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.DepartmentEntity;
import ch.etmles.payroll.Department.DepartmentService;
import ch.etmles.payroll.Exceptions.ResourceDeleteNotFound;
import ch.etmles.payroll.Exceptions.ResourceIDNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    public static final String RESSOURCE_NAME = "employee";
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceIDNotFound(id, RESSOURCE_NAME));
    }

    public EmployeeEntity create(EmployeeEntity newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    public EmployeeEntity update(Long id, EmployeeEntity updatedEmployee) {
        return employeeRepository.findById(id)
            .map(employee -> {
                employee.setEmail(updatedEmployee.getEmail());
                employee.setName(updatedEmployee.getName());
                employee.setFirstname(updatedEmployee.getFirstname());
                employee.setRole(updatedEmployee.getRole());
                return employeeRepository.save(employee);
            })
            .orElseGet(() -> {
                updatedEmployee.setId(id);
                return employeeRepository.save(updatedEmployee);
            });
    }

    public EmployeeEntity patch(Long id, Map<String, Object> values) {
        EmployeeEntity employee = getById(id);

        values.forEach((key, value) -> {
            switch (key) {
                case "id":
                    employee.setId(Long.parseLong(value.toString()));
                    break;
                case "email":
                    employee.setEmail((String) value);
                    break;
                case "name":
                    employee.setName((String) value);
                    break;
                case "firstname":
                    employee.setFirstname((String) value);
                    break;
                case "role":
                    employee.setRole((String) value);
                    break;
                case "department_id":
                    if (value != null) {
                        if (employee.getDepartment() != null) throw new EmployeeAlreadyInDepartment(id);
                        DepartmentEntity department = departmentService.getById(Long.parseLong(value.toString()));
                        employee.setDepartment(department);
                    } else {
                        if (employee.getDepartment() == null) throw new EmployeeNotInDepartment(id);
                        employee.setDepartment(null);
                    }
                    break;
            }
        });

        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceDeleteNotFound(id, RESSOURCE_NAME);
        }
    }
}
