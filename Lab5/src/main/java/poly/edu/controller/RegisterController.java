	package poly.edu.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.service.ParamService;

@Controller
public class RegisterController {

    @Autowired
    ParamService paramService;

    @GetMapping("/account/register")
    public String showForm() {
        return "/account/register";
    }

    @PostMapping("/account/register")
    public String register(Model model, MultipartFile photo) {
        try {
            String username = paramService.getString("username", "");
            String password = paramService.getString("password", "");
            MultipartFile file = photo;

            // Lưu file ảnh vào thư mục uploads
            File savedFile = paramService.save(file, "/uploads/");

            if (savedFile != null) {
                model.addAttribute("message", "Đăng ký thành công!");
                model.addAttribute("color", "green");
                model.addAttribute("filename", savedFile.getName());
            } else {
                model.addAttribute("message", "Vui lòng chọn hình để tải lên!");
                model.addAttribute("color", "red");
            }

        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
            model.addAttribute("color", "red");
        }

        return "/account/register";
    }
}
