package fstf.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Lab {
    @Id
    String nom;

    @Transient
    List<User> person;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<User> getPerson() {
        return person;
    }

    public void setPerson(List<User> person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "nom='" + nom + '\'' +
                ", person=" + person +
                '}';
    }
}
