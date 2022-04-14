package fstf.presentation;

import fstf.business.PanneManager;
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
public class GestionPanne {
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


}
