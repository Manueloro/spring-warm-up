package ch.etmles.payroll.Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Department {

    private @Id
    @GeneratedValue Long id;
    private @Column(unique = true) String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department(){}

    public Department(String name){
        this.setName(name);
    }

    public Long getID(){
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
        if(!(o instanceof Department employee))
            return false;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString(){
        return "Department{" + "id=" + this.getID() + ", name='" + this.getName() + '\'' + '}';
    }
}
