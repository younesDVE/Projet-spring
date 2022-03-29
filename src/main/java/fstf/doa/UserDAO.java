package fstf.doa;

import fstf.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,String> {
}
