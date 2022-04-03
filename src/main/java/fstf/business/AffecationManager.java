package fstf.business;

import fstf.doa.AffectationDAO;
import fstf.models.Affectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffecationManager {
    @Autowired
    AffectationDAO aff_dao;

    public void add(Affectation aff){
        aff_dao.save(aff);
    }
}
