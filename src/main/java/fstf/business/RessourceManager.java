package fstf.business;

import fstf.doa.AffectationDAO;
import fstf.doa.RessourceDAO;
import fstf.models.Affectation;
import fstf.models.Imprimente;
import fstf.models.ListeRessource;
import fstf.models.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RessourceManager {
    @Autowired
    RessourceDAO r_dao;

    @Autowired
    AffectationDAO a_dao;

    public boolean add(Ressource r){
        r_dao.save(r);
        return true;
    }

    public Ressource findById(String code){
        return r_dao.findById(code).orElse(null);
    }

    public void delete(String code){
        r_dao.delete(findById(code));
    }

    public List<ListeRessource> findAll(){
        List<ListeRessource> list = new ArrayList<>();
        ListeRessource lr;
        Affectation aff;
        for(Ressource r:r_dao.findAll()){
            aff = a_dao.findAffectationByRessource_Code(r.getCode());
            lr = new ListeRessource(r.getCode(),r instanceof Imprimente? "Imprimente":"Ordinateur",
                    r.getDate_liv(),r.getDuree_gar(),r.getMarque(),r.toString());
            if(aff!=null){
                lr.setAffectation(aff);
                lr.setAffacte(true);
            }
            list.add(lr);
        }
        return list;
    }
}
