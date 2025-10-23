package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import poly.edu.entity.Account;
import poly.edu.service.AccountService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        Account user = accountService.findByUsernameAndPassword(username, password);

        if (user == null) {
            model.addAttribute("message", "❌ Invalid username or password!");
        } else {
            // Lưu vào session
            session.setAttribute("user", user);

            // Gửi thông tin ra view
            model.addAttribute("message", "✅ Login successfully!");
            model.addAttribute("fullname", user.getFullname());
            model.addAttribute("email", user.getEmail());
        }
        return "auth/login";
    }
}
