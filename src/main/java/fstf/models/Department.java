package fstf.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Department {
    @Id
    String name;

    @Transient
    List<User> person;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getPerson() {
        return person;
    }

    public void setPerson(List<User> person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }
}
