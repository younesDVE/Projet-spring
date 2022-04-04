package fstf.business;

import fstf.doa.FournisseurDAO;
import fstf.doa.RessourceDAO;
import fstf.models.Fournisseur;
import fstf.models.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FournisseurManager {
    @Autowired
    FournisseurDAO f_dao;

    @Autowired
    RessourceDAO r_dao;

    public boolean add(Fournisseur f){
        f_dao.save(f);
        return true;
    }
    public List<String> findAll(){
        List<String> list = new ArrayList<>();
        for(Fournisseur f:f_dao.findAll()) list.add(f.getNom_soc());
        return list;
    }
    public List<Fournisseur> getList(){
        List<Fournisseur> list = new ArrayList<>();
        for(Fournisseur f:f_dao.findAll()) {
            List<Ressource> r = r_dao.findByFr(f.getNom_soc());
            if(r.size()==0) f.setDeletable(true);
            list.add(f);
        }
        return list;
    }

    public void delete(String id){
        f_dao.deleteById(id);
    }

    public Fournisseur findById(String nom_soc){
        return f_dao.findById(nom_soc).orElse(null);
    }
}
