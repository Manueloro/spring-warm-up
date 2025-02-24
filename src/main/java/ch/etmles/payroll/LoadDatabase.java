package ch.etmles.payroll;

import ch.etmles.payroll.Department.DepartmentEntity;
import ch.etmles.payroll.Department.DepartmentRepository;
import ch.etmles.payroll.Employee.EmployeeEntity;
import ch.etmles.payroll.Employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initEmployees(EmployeeRepository repository){
        return args->{
            log.info("Preloading " + repository.save(new EmployeeEntity("bilbo.baggins@ringlord.com", "Baggins", "Bilbo", "burglar", null)));
            log.info("Preloading " + repository.save(new EmployeeEntity("frodo.baggins@ringlord.com", "Baggins", "Frodo", "thief", null)));
        };
    }

    @Bean
    CommandLineRunner initDepartments(DepartmentRepository repository){
        return args->{
            log.info("Preloading " + repository.save(new DepartmentEntity("SIG")));
            log.info("Preloading " + repository.save(new DepartmentEntity("SRS")));
        };
    }
}
