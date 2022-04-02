package fstf.doa;

import fstf.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentDAO extends CrudRepository<Department,String> {
}
