package poly.edu.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;

@Controller
public class ProductController {

    @Autowired 
    ProductDAO dao;
    
    @Autowired 
    SessionService session; 

    @RequestMapping("/product/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {

        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }
    
    @RequestMapping("/product/search-and-page") 
    public String searchAndPage(Model model,  
                                @RequestParam("keywords") Optional<String> kw, 
                                @RequestParam("p") Optional<Integer> p) { 
        String kwords = kw.orElse(session.get("keywords", "")); 
        session.set("keywords", kwords); 
        Pageable pageable = PageRequest.of(p.orElse(0), 5); 
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable); 
        model.addAttribute("page", page); 
        return "product/search-and-page";
    }
}
