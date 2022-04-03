package fstf.doa;

import fstf.models.Affectation;
import org.springframework.data.repository.CrudRepository;

public interface AffectationDAO extends CrudRepository<Affectation,Integer> {
    public Affectation findAffectationByRessource_Code(String code);
}

