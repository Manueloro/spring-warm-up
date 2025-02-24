package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.DepartmentController;
import ch.etmles.payroll.Exceptions.RessourceDeleteNotFound;
import ch.etmles.payroll.Exceptions.RessourceIDNotFound;
import ch.etmles.payroll.Department.DepartmentEntity;
import ch.etmles.payroll.Department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {
    public static final String RESSOURCE_NAME = "employee";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /* curl sample :
    curl -i localhost:8080/employees
    */
    @GetMapping()
    List<EmployeeEntity> all(){
        return employeeRepository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/employees ^
        -H "Content-type:application/json" ^
        -d "{\"email\": \"george.russel@world.com\", \"name\": \"Russel\", \"firstname\": \"George\", \"role\": \"gardener\"}"
    */
    @PostMapping()
    EmployeeEntity newEmployee(@RequestBody EmployeeEntity newEmployee){
        return employeeRepository.save(newEmployee);
    }

    /* curl sample :
    curl -i localhost:8080/employees/1
    */
    @GetMapping("/{id}")
    EmployeeEntity one(@PathVariable Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceIDNotFound(id, RESSOURCE_NAME));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Samwise Bing\", \"role\": \"peer-to-peer\"}"
     */
    @PutMapping("/{id}")
    EmployeeEntity replaceEmployee(@RequestBody EmployeeEntity newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmail(newEmployee.getEmail());
                    employee.setName(newEmployee.getName());
                    employee.setFirstname(newEmployee.getFirstname());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2
    */
    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RessourceDeleteNotFound(id, RESSOURCE_NAME);
        }
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2/department/2
     */
    @PutMapping("/{employeeId}/department/{departmentId}")
    void joinDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId){
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RessourceIDNotFound(employeeId, RESSOURCE_NAME));

        if (employee.getDepartment() != null)
            throw new EmployeeAlreadyInDepartment(employeeId);

        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RessourceIDNotFound(departmentId, DepartmentController.RESSOURCE_NAME));

        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2/department
     */
    @DeleteMapping("/{employeeId}/department")
    void leaveDepartment(@PathVariable Long employeeId){
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RessourceIDNotFound(employeeId, RESSOURCE_NAME));

        if (employee.getDepartment() == null)
            throw new EmployeeNotInDepartment(employeeId);

        employee.setDepartment(null);
        employeeRepository.save(employee);
    }
}
