package fstf.presentation;

import fstf.business.PanneManager;
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
    @PostMapping("EnvoyerPanne")
    public String signalerPanne(Panne p){
        pm.save(p);
        System.out.println("hello");
        System.out.println(p);
        return"gg";

    }



    @RequestMapping("SignalerPanne")
    public ModelAndView signalerPanne()
    { ModelAndView mv= new ModelAndView("Personnel/signaler_panne.jsp");


        return mv;
    }
    @RequestMapping("ListerMyRessource")
    public ModelAndView listermyressource(HttpSession session) {
        //System.out.println(user);
        User user = (User) session.getAttribute("user");
        List<Ressource> listr = null;
        if (user instanceof Adminstratif) {
            //Adminstratif adm= (Adminstratif) user;
            listr = pm.listRessource((Adminstratif) user);
        }
        else
        {
            listr = pm.listRessource((Enseignant) user);
        }
        ModelAndView mv = new ModelAndView("Personnel/ressources.jsp");
        mv.addObject("ressources", listr);
        return mv;
    }


}
