package fstf.business;

import fstf.doa.*;
import fstf.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PanneManager {

    @Autowired
    PanneDAO pd;
    @Autowired
    AffectationDAO af;
    @Autowired
    AffectationDAO_E afe;
    @Autowired
    DepartmentDAO dd;
    @Autowired
    UserDAO ud;
    @Autowired
    EnseignantDAO ed;

    public void save(Panne p){
        pd.save(p);
    }

    public List<Ressource> listRessource(Adminstratif user)
    {
        List<Ressource> listt =new ArrayList<>();
        List<Affectation> listeee = new ArrayList<>();
        String ee= ud.findUserByNom(user.getNom()).getDepartment().getName();
       listeee =  af.findAffectationByDepartment(dd.findDepartmentByName(ee));

        if(listeee!=null){

            System.out.println("succes");
            for (Affectation s : listeee) {
                System.out.println(s.getRessource().getMarque());
                listt.add(s.getRessource());
            }
        }

        System.out.println("echec");
        return listt;

    }





    public List<Ressource> listRessource(Enseignant user)
    {
        List<Ressource> listt =new ArrayList<>();
        List<Affectation_E> listeee = new ArrayList<>();
        listeee =  afe.findAffectation_EByUser(user);

        if(listeee!=null){

            System.out.println("succes");
            for (Affectation s : listeee) {
                System.out.println(s.getRessource().getMarque());
                listt.add(s.getRessource());
            }
        }

        System.out.println("echec");
        return listt;

    }
}