package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import poly.edu.entity.Account;
import poly.edu.service.AccountService;

@Controller
@RequestMapping("/auth")
public class B5Controller {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/login2")
    public String loginForm(Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("message", session.getAttribute("message"));
        session.removeAttribute("message");
        return "auth/login2";
    }

    @PostMapping("/login2")
    public String loginProcess(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        Account user = accountService.findById(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
            return "auth/login2";
        }

        session.setAttribute("user", user);
        model.addAttribute("user", user);
        model.addAttribute("message", "Đăng nhập thành công!");

        // Nếu có URL bảo vệ trước đó → redirect
        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            session.removeAttribute("securityUri");
            return "redirect:" + securityUri;
        }

        // Nếu không có URL bảo vệ → vẫn ở login2
        return "auth/login2";
    }

}
