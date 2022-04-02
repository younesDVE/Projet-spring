package fstf.business;

import fstf.doa.DepartmentDAO;
import fstf.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentManager {
    @Autowired
    DepartmentDAO d_dao;

    public void add(Department d){
        d_dao.save(d);
    }

    public List<String> findAll(){
        List<String> list = new ArrayList<>();
        for(Department d:d_dao.findAll()) list.add(d.getName());
        return list;
    }
}
