package fstf.doa;

import fstf.models.Affectation;
import fstf.models.Affectation_E;
import fstf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AffectationDAO_E extends JpaRepository<Affectation_E,Integer> {
    List <Affectation_E>  findAffectation_EByUser(User user);

}
