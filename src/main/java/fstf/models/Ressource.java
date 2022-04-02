package fstf.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ressource {
    @Id
    String code;
    String date_liv;
    Integer duree_gar;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "nom_soc",referencedColumnName = "nom_soc")
    Fournisseur fr;
}
