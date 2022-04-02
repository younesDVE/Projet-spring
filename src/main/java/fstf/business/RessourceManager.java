package fstf.business;

import fstf.doa.RessourceDAO;
import fstf.models.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RessourceManager {
    @Autowired
    RessourceDAO r_dao;

    public boolean add(Ressource r){
        r_dao.save(r);
        return true;
    }
}
