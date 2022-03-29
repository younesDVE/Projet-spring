package fstf.presentation;

import fstf.business.AccountManager;
import fstf.models.Account;
import fstf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class Responsable {
    @Autowired
    AccountManager account_manager;

    @GetMapping("/AddAccount")
    public String addAccount(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user.getAccount().getRole() != 1) return "unauthorized.html";

        System.out.println("Request for adding account");
        return "responsable/addAccount.html";
    }

    @PostMapping("/AddAccount")
    public ModelAndView addAccount(Account a, HttpSession session) throws ServletException, IOException {
        System.out.println("Sendind Account data & request for next");
        System.out.println(a);
        ModelAndView mv = new ModelAndView("responsable/addUser.jsp");
        mv.addObject("account",a);
        session.setAttribute("account",a);
        return mv;
    }

    @PostMapping("/AddUser")
    public String addAccount(User u, HttpSession session){
        Account a = (Account) session.getAttribute("account");
        u.setAccount(a);
        u.setUser_id(a.getAccount_id());
        System.out.println("Sending User Data");
        System.out.println(u);
        session.removeAttribute("account");
        if(account_manager.addAccount(u,a))
            return "responsable/index.jsp";
        else
            return "error.html";
    }
}

