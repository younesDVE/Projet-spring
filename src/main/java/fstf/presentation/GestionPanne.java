package fstf.presentation;

import fstf.business.ConstatManager;
import fstf.business.PanneManager;
import fstf.business.RessourceManager;
import fstf.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GestionPanne {
    @Autowired
    ConstatManager cm;
    @Autowired
    PanneManager pm;
    @Autowired
    RessourceManager rm;
    @PostMapping("EnvoyerPanne")
    public String signalerPanne(Panne p){
        pm.save(p);
        System.out.println("hello");
        System.out.println(p);
        return"gg";

    }



    @RequestMapping("SignalerPanne")
    public ModelAndView signalerpanne(String code , HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Ressource r = rm.findById(code);
        System.out.println(r.getCode());
        User user = (User) session.getAttribute("user");
            if (user instanceof Adminstratif) {
                mv.setViewName("admin/signaler_panne.jsp");
            }

            if (user instanceof Enseignant) {
                mv.setViewName("prof/signaler_panne.jsp");
            }
        mv.addObject("Ressource", r);
        return mv;

    }

    @RequestMapping("ListerMyRessource")
    public ModelAndView listermyressource(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<Ressource> listr = null;
        if (user instanceof Adminstratif){
            mv.setViewName("admin/ressources.jsp");
            listr = pm.listRessource((Adminstratif) user);
        }

        if (user instanceof Enseignant){
            mv.setViewName("prof/ressources.jsp");
            listr = pm.listRessource((Enseignant) user);
        }

        mv.addObject("ressources", listr);
        return mv;
    }

    @RequestMapping("ListerPanne")
    public ModelAndView listerpanne(){
        ModelAndView mv = new ModelAndView();
       List<Panne> listp= pm.listpanne();
       mv.setViewName("maintenance/pannes.jsp");
       mv.addObject("liste",listp);
     return mv;
    }

    @RequestMapping("ConsulterPanne")
    public ModelAndView consulterpanne(Integer id){
        ModelAndView mv = new ModelAndView();
        Panne pan= pm.findById(id);
        mv.setViewName("maintenance/consulterpanne.jsp");
        mv.addObject("panne",pan);
        return mv;
    }

    @RequestMapping("DeletePanne")
    public String deletepanne(Integer id){
        pm.deleteById(id);
        return "redirect:/ListerPanne";
    }

    @RequestMapping("SaisirConstat")
    public ModelAndView saisirconstat(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("maintenance/genererconstat.jsp");
        return mv;
    }
    @PostMapping("AddConstat")
    public void addconstat(Constat c)
    {
     cm.save(c);
     System.out.println(c.toString());
    }

    @RequestMapping("ListerConstat")
    public ModelAndView listerconstat(){
        ModelAndView mv = new ModelAndView();
        List<Constat> listc= cm.listconstat();
        mv.setViewName("responsable/constat.jsp");
        mv.addObject("liste",listc);
        return mv;
    }

}
