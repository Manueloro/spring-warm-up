package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.DepartmentEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class EmployeeEntity {

    private @Id
    @GeneratedValue Long id;
    private @Column(unique = true) String email;
    private String name;
    private String firstname;
    private String role;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    public EmployeeEntity(){}

    public EmployeeEntity(String email, String name, String firstname, String role, DepartmentEntity department){
        this.setEmail(email);
        this.setName(name);
        this.setFirstname(firstname);
        this.setRole(role);
        this.setDepartment(department);
    }

    public Long getID(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){ this.email = email; }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String firstname){ this.firstname = firstname; }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public DepartmentEntity getDepartment() { return this.department; }

    public void setDepartment(DepartmentEntity department) { this.department = department; }


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof EmployeeEntity employee))
            return false;
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.email, employee.email)
                && Objects.equals(this.name, employee.name)
                && Objects.equals(this.firstname, employee.firstname)
                && Objects.equals(this.role, employee.role)
                && Objects.equals(this.department, employee.department);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.email, this.name, this.firstname, this.role, this.department);
    }

    @Override
    public String toString(){
        return "Employee{"
                + "id=" + this.getID()
                + ", email='" + this.getEmail() + '\''
                + ", name='" + this.getName()+ '\''
                + ", firstname='" + this.getFirstname() + '\''
                + ", role='" + this.getRole() + '\''
                + ", department='" + this.getDepartment() + '\''
                + '}';
    }
}
