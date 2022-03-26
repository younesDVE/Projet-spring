package ma.fsts.projetgl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        if(user_id == null) return "/Login";
        System.out.println("User visited Home Page with id=" + user_id);
        return "index.jsp";
    }

    @RequestMapping("Logout")
    public String logout(HttpSession session){
        String user_id = (String) session.getAttribute("user_id");
        session.removeAttribute("user_id");
        System.out.println(user_id + " is logout");
        return "redirect:/Login";
    }

    @RequestMapping("/Login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login.html");
        System.out.println("User trying to login");
        return mv;
    }

    @PostMapping("/Authentication")
    public String Authentication(String username,String password,HttpSession session){
        session.setAttribute("user_id",username);
        System.out.println("User authenticated as : " + username + "_" + password);
        return "redirect:/";
    }
}
