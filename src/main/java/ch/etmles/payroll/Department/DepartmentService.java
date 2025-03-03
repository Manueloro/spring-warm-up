package ch.etmles.payroll.Department;

import ch.etmles.payroll.Exceptions.ResourceDeleteNotFound;
import ch.etmles.payroll.Exceptions.ResourceIDNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    public static final String RESSOURCE_NAME = "department";

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentEntity> getAll() {
        return departmentRepository.findAll();
    }

    public DepartmentEntity getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceIDNotFound(id, RESSOURCE_NAME));
    }

    public DepartmentEntity create(DepartmentEntity newDepartment) {
        return departmentRepository.save(newDepartment);
    }

    public DepartmentEntity update(Long id, DepartmentEntity updatedDepartment) {
        return departmentRepository.findById(id)
            .map(department -> {
                department.setName(updatedDepartment.getName());
                return departmentRepository.save(department);
            })
            .orElseGet(() -> {
                updatedDepartment.setId(id);
                return departmentRepository.save(updatedDepartment);
            });
    }

    public void delete(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new ResourceDeleteNotFound(id, RESSOURCE_NAME);
        }
    }
}
