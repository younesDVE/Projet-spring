package fstf.presentation;

import fstf.business.AccountManager;
import fstf.models.Account;
import fstf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    AccountManager account_manager;

    @RequestMapping("Init")
    @ResponseBody
    public String ima(){
      Account a = new Account();
      a.setEmail("idrissi@gmail.com");
      a.setPassword("123456");
      a.setRole(1);
      a.setAccount_id("M123456");

      User u = new User();
      u.setNom("IDRISSI");
      u.setPrenom("YOUNESS");
      u.setUser_id("M123456");

      if(account_manager.add(u,a))
          return "Account created successfully";
      else
          return "Failed to creat account!";
    }

    @RequestMapping(value = "/")
    public ModelAndView home(HttpSession session){
        ModelAndView mv = new ModelAndView("index.jsp");
        User user = (User) session.getAttribute("user");
        if(user == null){
            mv.setViewName("/Login");
            return mv;
        }
        int role = user.getAccount().getRole();
        System.out.println("User visited Home Page with id=" + user.getAccount().getEmail());

        if(role == 1) mv.setViewName("responsable/index.jsp");
        if(role == 2) mv.setViewName("personnel/index.jsp");
        return mv;
    }

    @RequestMapping("Logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        System.out.println("User Loged out");
        return "redirect:/Login";
    }

    @RequestMapping("/Login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login.html");
        System.out.println("User trying to login");
        return mv;
    }

    @PostMapping("/Authentication")
    public String Authentication(String user_cin,String user_pwd,HttpSession session){
        User u = account_manager.authenticate(user_cin,user_pwd);
        if(u!=null){
            session.setAttribute("user",u);
            System.out.println("User authenticated as : " + u.getNom() + "_" + u.getPrenom() + "_" + u.getAccount().getEmail());
            return "redirect:/";
        }else{
            System.out.println("user cin or password are incorrect!");
            return "redirect:/Login";
        }
    }
}
