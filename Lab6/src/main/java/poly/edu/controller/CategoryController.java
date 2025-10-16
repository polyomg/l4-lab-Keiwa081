package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.dao.CategoryDAO;
import poly.edu.entity.Category;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryDAO dao;

    @GetMapping("/index")
    public String index(Model model) {
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Category item = dao.findById(id).orElse(new Category());
        model.addAttribute("item", item);
        model.addAttribute("items", dao.findAll());
        return "category/index";
    }

    @PostMapping("/create")
    public String create(Category item) {
        dao.save(item);
        return "redirect:/category/index";
    }

    @PostMapping("/update")
    public String update(Category item) {
        dao.save(item);
        return "redirect:/category/edit/" + item.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        dao.deleteById(id);
        return "redirect:/category/index";
    }
}