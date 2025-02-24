package ch.etmles.payroll.Department;

import ch.etmles.payroll.Exceptions.RessourceDeleteNotFound;
import ch.etmles.payroll.Exceptions.RessourceIDNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/departments")
public class DepartmentController {
    public static final String RESSOURCE_NAME = "department";

    @Autowired
    private DepartmentRepository repository;

    /* curl sample :
    curl -i localhost:8080/departments
    */
    @GetMapping()
    List<DepartmentEntity> all(){
        return repository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/departments ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"MECA\"}"
    */
    @PostMapping()
    DepartmentEntity newDepartment(@RequestBody DepartmentEntity newDepartment){
        return repository.save(newDepartment);
    }

    /* curl sample :
    curl -i localhost:8080/departments/1
    */
    @GetMapping("/{id}")
    DepartmentEntity one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RessourceIDNotFound(id, RESSOURCE_NAME));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/departments/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Telecom\"}"
     */
    @PutMapping("/{id}")
    DepartmentEntity replaceDepartment(@RequestBody DepartmentEntity newDepartment, @PathVariable Long id) {
        return repository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    return repository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return repository.save(newDepartment);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/departments/2
    */
    @DeleteMapping("/{id}")
    void deleteDepartment(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RessourceDeleteNotFound(id, RESSOURCE_NAME);
        }
    }
}
