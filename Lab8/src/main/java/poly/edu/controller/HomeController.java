package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    
    @GetMapping("/home/index")
    public String home(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        return "home/index";
    }


}
