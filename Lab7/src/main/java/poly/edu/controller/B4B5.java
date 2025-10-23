package poly.edu.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.dao.ProductDAO2;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;

@Controller
public class B4B5 {

    @Autowired 
    ProductDAO2 dao;
    
    @Autowired 
    SessionService session; 
    
    @RequestMapping("/product/search2")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {

        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search2";
    }
 
    @RequestMapping("/product/search-and-page2") 
    public String searchAndPage(Model model,  
                                @RequestParam("keywords") Optional<String> kw, 
                                @RequestParam("p") Optional<Integer> p) { 
        
        String kwords = kw.orElse(session.get("keywords", "")); 
        session.set("keywords", kwords); 

        int pageNumber = p.orElse(0);
        if (pageNumber < 0) {
            pageNumber = 0;
        }

        Pageable pageable = PageRequest.of(pageNumber, 5); 
        Page<Product> page = dao.findAllByNameLike("%" + kwords + "%", pageable); 

        // ✅ Nếu người dùng nhập p lớn hơn tổng số trang, tự động quay về trang cuối
        if (pageNumber >= page.getTotalPages() && page.getTotalPages() > 0) {
            pageable = PageRequest.of(page.getTotalPages() - 1, 5);
            page = dao.findAllByNameLike("%" + kwords + "%", pageable);
        }

        model.addAttribute("page", page); 
        model.addAttribute("keywords", kwords);
        return "product/search-and-page2";
    }
}
