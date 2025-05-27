package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeControl {

    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @GetMapping("/") 
    public String home(Model model) {
        List<Product> productList = productDao.getAllProducts();
        List<Category> categoryList = categoryDao.getAllCategories();
        
        model.addAttribute("product_list", productList);
        model.addAttribute("category_list", categoryList);
        model.addAttribute("home_active", "active");

        return "index";
    }
}
