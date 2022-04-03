package fstf.doa;

import fstf.models.Enseignant;
import fstf.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User,String> {

}
