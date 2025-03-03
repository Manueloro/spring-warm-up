package ch.etmles.payroll.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /* curl sample :
    curl -i localhost:8080/departments
    */
    @GetMapping()
    List<DepartmentEntity> all(){
        return departmentService.getAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/departments ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"MECA\"}"
    */
    @PostMapping()
    DepartmentEntity newDepartment(@RequestBody DepartmentEntity newDepartment){
        return departmentService.create(newDepartment);
    }

    /* curl sample :
    curl -i localhost:8080/departments/1
    */
    @GetMapping("/{id}")
    DepartmentEntity one(@PathVariable Long id){
        return departmentService.getById(id);
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/departments/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Telecom\"}"
     */
    @PutMapping("/{id}")
    DepartmentEntity replaceDepartment(@RequestBody DepartmentEntity newDepartment, @PathVariable Long id) {
        return departmentService.update(id, newDepartment);
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/departments/2
    */
    @DeleteMapping("/{id}")
    void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
