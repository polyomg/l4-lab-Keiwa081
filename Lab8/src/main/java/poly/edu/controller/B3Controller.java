package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.service.MailService;
import poly.edu.service.MailService.Mail;

import java.io.File;


@Controller
@RequestMapping("/mail")
public class B3Controller {

	@Autowired
    MailService mailService;

    // Hiển thị form gửi mail
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("mail", new Mail());
        return "mail-form";
    }

    // Gửi trực tiếp (send)
    @PostMapping("/send")
    public String sendMail(@ModelAttribute("mail") Mail mail, Model model) {
        try {
            mailService.send(mail);
            model.addAttribute("message", "✅ Gửi mail trực tiếp thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "❌ Gửi mail thất bại: " + e.getMessage());
        }
        return "mail-form";
    }

    // Đưa vào hàng đợi (push)
    @PostMapping("/queue")
    public String queueMail(@ModelAttribute("mail") Mail mail, Model model) {
        try {
            mailService.push(mail);
            model.addAttribute("message", "📨 Mail đã được thêm vào hàng đợi!");
        } catch (Exception e) {
            model.addAttribute("message", "❌ Thêm mail vào hàng đợi thất bại: " + e.getMessage());
        }
        return "mail-form";
    }
}
