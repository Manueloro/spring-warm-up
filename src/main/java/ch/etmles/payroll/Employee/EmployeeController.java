package ch.etmles.payroll.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /* curl sample :
    curl -i localhost:8080/employees
    */
    @GetMapping()
    List<EmployeeEntity> all(){
        return employeeService.getAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/employees ^
        -H "Content-type:application/json" ^
        -d "{\"email\": \"george.russel@world.com\", \"name\": \"Russel\", \"firstname\": \"George\", \"role\": \"gardener\"}"
    */
    @PostMapping()
    EmployeeEntity newEmployee(@RequestBody EmployeeEntity newEmployee){
        return employeeService.create(newEmployee);
    }

    /* curl sample :
    curl -i localhost:8080/employees/1
    */
    @GetMapping("/{id}")
    EmployeeEntity one(@PathVariable Long id){
        return employeeService.getById(id);
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Samwise Bing\", \"role\": \"peer-to-peer\"}"
     */
    @PutMapping("/{id}")
    EmployeeEntity replaceEmployee(@RequestBody EmployeeEntity newEmployee, @PathVariable Long id) {
        return employeeService.update(id, newEmployee);
    }

    /* curl sample :
    curl -i -X PATCH localhost:8080/employees/2 ^
        -H "Content-type:application/json" ^
        -d "{\"department_id\": 2}"
     */
    @PatchMapping("/{id}")
    EmployeeEntity editEmployee(@RequestBody Map<String, Object> editedEmployee, @PathVariable Long id) {
        return employeeService.patch(id, editedEmployee);
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2
    */
    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
