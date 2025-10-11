package poly.edu.controller;


import poly.edu.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
	@Autowired 
	CookieService cookieService; 
	@Autowired 
	ParamService paramService; 
	@Autowired 
	SessionService sessionService;
	
	@GetMapping("/account/login")
	public String login1(Model model) {
		String rememberedUser = cookieService.getValue("user");
		model.addAttribute("username", rememberedUser);
		return "/account/login";
	}

	@PostMapping("/account/login")
	public String login2(Model model) {
		String un = paramService.getString("username", ""); 
	String pw = paramService.getString("password", ""); 
	boolean rm = paramService.getBoolean("remember", false); 
	
		if (un.equals("poly") && pw.equals("123")) {
			sessionService.set("username", un);
			
			if (rm) {
				cookieService.add("user", un, 10*60);
			}else {
				cookieService.remove("user");
			}
			
			model.addAttribute("message","Đăng nhập thành công");
			model.addAttribute("color", "green");
			return "/account/login";
		}else {
			model.addAttribute("message", "Đăng nhập sai tên hoặc mật khẩu");
			model.addAttribute("color", "red");
			return "/account/login";
		}
		
	}
	
	
	
	
}
