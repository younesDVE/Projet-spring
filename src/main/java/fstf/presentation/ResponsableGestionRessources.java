package fstf.presentation;

import fstf.business.AffecationManager;
import fstf.business.DepartmentManager;
import fstf.business.FournisseurManager;
import fstf.business.RessourceManager;
import fstf.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ResponsableGestionRessources {
    @Autowired
    FournisseurManager fournisseur_manager;

    @Autowired
    RessourceManager ressource_manager;

    @Autowired
    DepartmentManager department_manager;


    @Autowired
    AffecationManager affecation_manager;

    @RequestMapping("SaisirRessource")
    public ModelAndView saisirRessource(){
        ModelAndView mv = new ModelAndView("responsable/saisir_ressource.jsp");
        List<String> listeFournisseurs = fournisseur_manager.findAll();
        mv.addObject("listFournisseurs",listeFournisseurs);
        return mv;
    }

    @PostMapping("addRessource")
    public ModelAndView addRessource(Ressource r, String nom_soc, HttpSession session){
        ModelAndView mv = new ModelAndView();
        Fournisseur f = fournisseur_manager.findById(nom_soc);
        r.setFr(f);
        session.setAttribute("ressource",r);
        if(f==null){//fournisseur ne se trouve pas dans la base des données
            session.setAttribute("ressource",r);
            mv.addObject("nom_soc",nom_soc);
            mv.setViewName("responsable/ajouter_fournisseur.jsp");
        }else{
            if(r.getType().equals("I")) mv.setViewName("responsable/ajouter_impremente.jsp");
            else mv.setViewName("responsable/ajouter_ordinateur.jsp");
        }
        System.out.println(r + "\n" + nom_soc + ":" + nom_soc.split(",").length);
        return mv;
    }

    @PostMapping("AddFournisseur")
    public ModelAndView addFournisseur(Fournisseur f,HttpSession session){
        ModelAndView mv = new ModelAndView();
        Ressource r = (Ressource) session.getAttribute("ressource");
        fournisseur_manager.add(f);
        if(r!=null){
            r.setFr(f);
            session.setAttribute("ressource",r);
            if(r.getType().equals("I")) mv.setViewName("responsable/ajouter_impremente.jsp");
            else mv.setViewName("responsable/ajouter_ordinateur.jsp");
        }
        return mv;
    }

    @PostMapping("AddImprimente")
    public String AddImprimente(Imprimente i, HttpSession session){
        Ressource r = (Ressource) session.getAttribute("ressource");
        i.copy(r);
        session.removeAttribute("ressource");
        ressource_manager.add(i);
        System.out.println(i);
        return "responsable/index.jsp";
    }

    @PostMapping("AddOrdinateur")
    public String addAddOrdinateur(Ordinateur o, HttpSession session){
        Ressource r = (Ressource) session.getAttribute("ressource");
        o.copy(r);
        session.removeAttribute("ressource");
        ressource_manager.add(o);
        System.out.println(o);
        return "responsable/index.jsp";
    }

    @RequestMapping("RessourcesList")
    public ModelAndView ressources_list(){
        List<ListeRessource> l = ressource_manager.findAll();
        ModelAndView mv = new ModelAndView("responsable/ressources.jsp");
        mv.addObject("ressources",l);
        return mv;
    }

    @PostMapping("RessourceAction")
    public String action(String code,String action,HttpSession session){
        if(action.equals("delete")){
            ressource_manager.delete(code);
            return "redirect:/RessourcesList";
        }

        if(action.equals("affect")){
            session.setAttribute("code",code);
            return "redirect:/AffectationDepartment";
        }

        return  "responsable/ressources.jsp";
    }

    @RequestMapping("AffectationDepartment")
    public ModelAndView affecation(HttpSession session){
        ModelAndView mv = new ModelAndView("responsable/affectationD.jsp");
        mv.addObject("deps",department_manager.findAll());
        return mv;
    }

    @PostMapping("AffecterDepartment")
    public ModelAndView affecterDepartment(String name,String action,HttpSession session){
        ModelAndView mv = new ModelAndView();
        if(action.equals("valider")){
            String code = (String) session.getAttribute("code");
            Affectation aff = new Affectation();
            Department d = department_manager.findById(name);
            Ressource  r = ressource_manager.findById(code);
            aff.setDepartment(d);
            aff.setRessource(r);
            affecation_manager.add(aff);
            List<ListeRessource> l = ressource_manager.findAll();
            mv.setViewName("responsable/ressources.jsp");
            mv.addObject("ressources",l);
        }
        return mv;
    }
}
