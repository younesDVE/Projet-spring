package fstf.presentation;

import fstf.business.AccountManager;
import fstf.business.FournisseurManager;
import fstf.business.RessourceManager;
import fstf.models.Account;
import fstf.models.Fournisseur;
import fstf.models.Ressource;
import fstf.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Responsable {
    @Autowired
    AccountManager account_manager;

    @Autowired
    FournisseurManager fournisseur_manager;

    @Autowired
    RessourceManager ressource_manager;

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
        if(account_manager.add(u,a))
            return "responsable/index.jsp";
        else
            return "error.html";
    }

    @RequestMapping("SaisirRessource")
    public ModelAndView saisirRessource(){
        ModelAndView mv = new ModelAndView("responsable/saisir_ressource.jsp");
        List<String> listeFournisseurs = fournisseur_manager.findAll();
        mv.addObject("listFournisseurs",listeFournisseurs);
        return mv;
    }

    @PostMapping("addRessource")
    public ModelAndView addRessource(Ressource r,String nom_soc,HttpSession session){
        ModelAndView mv = new ModelAndView("responsable/saisir_ressource.jsp");
        Fournisseur f = fournisseur_manager.findById(nom_soc);
        if(f==null){
            session.setAttribute("ressource",r);
            mv.addObject("nom_soc",nom_soc);
            mv.setViewName("responsable/ajouter_fournisseur.jsp");
        }else{
            r.setFr(f);
            ressource_manager.add(r);
            mv.setViewName("responsable/index.jsp");
        }

        System.out.println(r + "\n" + nom_soc + ":" + nom_soc.split(",").length);
        return mv;
    }

    @PostMapping("AddFournisseur")
    public String addFournisseur(Fournisseur f,HttpSession session){
        Ressource r = (Ressource) session.getAttribute("ressource");
        fournisseur_manager.add(f);
        if(r!=null){
            r.setFr(f);
            ressource_manager.add(r);
        }
        return "responsable/index.jsp";
    }
}

