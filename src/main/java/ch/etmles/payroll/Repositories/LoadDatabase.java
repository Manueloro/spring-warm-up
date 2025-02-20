package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Department;
import ch.etmles.payroll.Entities.Employee;
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
            log.info("Preloading " + repository.save(new Employee("bilbo.baggins@ringlord.com", "Baggins", "Bilbo", "burglar", null)));
            log.info("Preloading " + repository.save(new Employee("frodo.baggins@ringlord.com", "Baggins", "Frodo", "thief", null)));
        };
    }

    @Bean
    CommandLineRunner initDepartments(DepartmentRepository repository){
        return args->{
            log.info("Preloading " + repository.save(new Department("SIG")));
            log.info("Preloading " + repository.save(new Department("SRS")));
        };
    }
}
