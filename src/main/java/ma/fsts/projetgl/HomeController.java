package ma.fsts.projetgl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String home(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        if(user_id == null) return "/Login";
        System.out.println("User visited Home Page with id=");
        session.removeAttribute("user_id");
        return "index.html";
    }

    @RequestMapping("/Login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login.html");
        System.out.println("User will try to login");
        return mv;
    }

    @PostMapping("/Authentication")
    public String Authentication(HttpSession session){
        session.setAttribute("user_id","1");
        System.out.println("User authenticated as : ");
        return "redirect:/";
    }
}
