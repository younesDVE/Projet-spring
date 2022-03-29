package fstf.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Adminstratif extends User{
    private  String lab;
}
