package poly.edu.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import poly.edu.service.MailService;

@Controller
public class MailController {

    @Autowired
    MailService mailService;
     
//	@ResponseBody
//	@RequestMapping("/mail/send")
//	public String send() {
//		try {
//			mailService.send("lehoanganhkha2007@gmail.com", "Shopweb xin chào", "Xin chào bạn");
//			return "Mail của bạn đã được gửi đi";
//		} catch (MessagingException e) {
//			return e.getMessage();
//		}
//	}
	
	@ResponseBody 
	@RequestMapping("/mail/send") 
	public String send(Model model) { 
	mailService.push("lehoanganhkha2007@gmail.com", "Subject", "Body"); 
	return "Mail của bạn đã được xếp vào hàng đợi"; 
	} 
}
