package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductDAO dao;

    @GetMapping("/sort")
    public String sort(
            Model model,
            @RequestParam("field") Optional<String> field,
            @RequestParam("direction") Optional<String> direction
    ) {
        String sortField = field.orElse("price");
        String sortDir = direction.orElse("desc");

        // Đảo chiều nếu người dùng bấm lại cùng field
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(Sort.Direction.ASC, sortField)
                : Sort.by(Sort.Direction.DESC, sortField);

        List<Product> items = dao.findAll(sort);

        // Gửi dữ liệu ra view
        model.addAttribute("items", items);
        model.addAttribute("field", sortField.toUpperCase());
        model.addAttribute("direction", sortDir);

        // Xác định chiều sắp tới (để gán vào link)
        String reverseDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseDir", reverseDir);

        return "product/sort";
    }


    @GetMapping("/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        return "product/page";
    }

    @GetMapping("/page-sort")
    public String paginateAndSort(Model model,
                                  @RequestParam("p") Optional<Integer> p,
                                  @RequestParam("field") Optional<String> field) {
        Sort sort = Sort.by(Sort.Direction.DESC, field.orElse("price"));
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("field", field.orElse("price").toUpperCase());
        return "product/page-sort";
    }
}
