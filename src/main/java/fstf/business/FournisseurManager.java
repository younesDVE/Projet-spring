package fstf.business;

import fstf.doa.FournisseurDAO;
import fstf.models.Fournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FournisseurManager {
    @Autowired
    FournisseurDAO f_dao;

    public boolean add(Fournisseur f){
        f_dao.save(f);
        return true;
    }
    public List<String> findAll(){
        List<String> list = new ArrayList<>();
        for(Fournisseur f:f_dao.findAll()) list.add(f.getNom_soc());
        return list;
    }

    public Fournisseur findById(String nom_soc){
        return f_dao.findById(nom_soc).orElse(null);
    }
}
