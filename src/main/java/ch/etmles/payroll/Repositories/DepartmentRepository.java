package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Department;
import ch.etmles.payroll.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
