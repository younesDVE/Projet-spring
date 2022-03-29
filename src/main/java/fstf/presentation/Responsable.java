package fstf.presentation;

import fstf.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class Responsable {
    @RequestMapping("/AddAccount")
    @ResponseBody
    public String addAcount(HttpSession session){
        User user = (User) session.getAttribute("user");

        return user.getAccount().getEmail();
    }
}

