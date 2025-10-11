	package poly.edu.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String register(Model model, @RequestParam("photo") MultipartFile photo) {
        try {
            String username = paramService.getString("username", "");
            String password = paramService.getString("password", "");

            if (photo.isEmpty()) {
                model.addAttribute("message", "Vui lòng chọn hình để tải lên!");
                model.addAttribute("color", "red");
                return "/account/register";
            }

            // Đường dẫn thật trong thư mục static (để truy cập được bằng URL)
            String uploadDir = new File("src/main/resources/static/uploads").getAbsolutePath();
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu chưa có
            }

            // Lưu file vào thư mục
            String fileName = photo.getOriginalFilename();
            File dest = new File(directory, fileName);
            photo.transferTo(dest);

            model.addAttribute("message", "Đăng ký thành công!");
            model.addAttribute("color", "green");
            model.addAttribute("filename", "uploads/" + fileName);

        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
            model.addAttribute("color", "red");
        }

        return "/account/register";
    }

}
