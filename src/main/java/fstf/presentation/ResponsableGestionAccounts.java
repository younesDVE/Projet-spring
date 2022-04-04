package fstf.presentation;

import fstf.business.*;
import fstf.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ResponsableGestionAccounts {
    @Autowired
    AccountManager account_manager;

    @Autowired
    DepartmentManager department_manager;

    @Autowired
    LabManager lab_manager;

    @RequestMapping("AccountList")
    public ModelAndView accountList(){
        List<User> l = account_manager.getList();
        ModelAndView mv = new ModelAndView("responsable/accounts.jsp");
        mv.addObject("accounts",l);
        System.out.println(l);

        return mv;
    }

    @GetMapping("/AddAccount")
    public String addAccount(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user.getAccount().getRole() != 1) return "unauthorized.html";

        System.out.println("Request for adding account");
        return "responsable/addAccount.html";
    }

    @PostMapping("/AddAccount")
    public ModelAndView addAccount(Account a, HttpSession session) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("responsable/addUser.jsp");
        session.setAttribute("account",a);

        if(a.getRole()==3) //Administratif
            mv.setViewName("responsable/addAdmin.jsp");
        if(a.getRole()==4) { //Enseignant
            mv.addObject("deps",department_manager.findAll());
            mv.addObject("labs",lab_manager.findAll());
            mv.setViewName("responsable/addProf.jsp");
        }
        if(a.getRole()<3) mv.addObject("action","AddUser");
        System.out.println(a);
        return mv;
    }

    @PostMapping("/AddUser")
    public String addAccount(User u, HttpSession session){
        Account a = (Account) session.getAttribute("account");
        u.setAccount(a);
        u.setUser_id(a.getAccount_id());
        System.out.println(u);
        session.removeAttribute("account");
        if(account_manager.add(u,a))
            return "responsable/index.jsp";
        else
            return "error.html";
    }

    @PostMapping("AddAdmin")
    public String addAccount(Adminstratif u, HttpSession session){
        Account    a = (Account) session.getAttribute("account");
        Department d = (Department)  session.getAttribute("department");
        u.setAccount(a);
        u.setUser_id(a.getAccount_id());
        u.setDepartment(d);
        System.out.println(u);
        session.removeAttribute("account");
        session.removeAttribute("department");
        if(account_manager.add(u,a))
            return "responsable/index.jsp";
        else
            return "error.html";
    }

    @PostMapping("AddProf")
    public String addAccount(Enseignant u, HttpSession session){
        Account    a = (Account) session.getAttribute("account");
        Department d = (Department)  session.getAttribute("department");
        Lab        l = (Lab)  session.getAttribute("lab");
        u.setAccount(a);
        u.setLab(l);
        u.setUser_id(a.getAccount_id());
        u.setDepartment(d);
        System.out.println(u);
        session.removeAttribute("account");
        session.removeAttribute("department");
        session.removeAttribute("lab");
        if(account_manager.add(u,a))
            return "responsable/index.jsp";
        else
            return "error.html";
    }


    @PostMapping("AddDepartment")
    public ModelAndView addDepartment(Department d, HttpSession session){
        ModelAndView mv = new ModelAndView("responsable/addUser.jsp");
        session.setAttribute("department",d);
        department_manager.add(d);
        System.out.println(d);
        mv.addObject("action","AddAdmin");
        return mv;
    }
    @PostMapping("AddLab")
    public ModelAndView addLab(Department d,Lab l, HttpSession session){
        ModelAndView mv = new ModelAndView("responsable/addUser.jsp");
        session.setAttribute("lab",l);
        session.setAttribute("department",d);
        lab_manager.add(l);
        System.out.println(l);
        System.out.println(d);
        mv.addObject("action","AddProf");
        return mv;
    }
}

