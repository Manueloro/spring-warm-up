package ch.etmles.payroll.Department;

import ch.etmles.payroll.Employee.EmployeeEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class DepartmentEntity {

    private @Id
    @GeneratedValue Long id;
    private @Column(unique = true) String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

    public DepartmentEntity(){}

    public DepartmentEntity(String name){
        this.setName(name);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof DepartmentEntity department))
            return false;
        return Objects.equals(this.id, department.id) && Objects.equals(this.name, department.name) && Objects.equals(this.employees, department.employees);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.employees);
    }

    @Override
    public String toString(){
        return "Department{" + "id=" + this.getId() + ", name='" + this.getName() + '\'' + '}';
    }
}
