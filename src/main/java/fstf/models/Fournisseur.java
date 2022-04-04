package fstf.models;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@NoArgsConstructor
public class Fournisseur {
    @Id
    String nom_soc;
    String email;
    String address;

    @Transient
    boolean deletable = false;

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public String getNom_soc() {
        return nom_soc;
    }

    public void setNom_soc(String nom_soc) {
        this.nom_soc = nom_soc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "nom_soc='" + nom_soc + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", deletable=" + deletable +
                '}';
    }
}
