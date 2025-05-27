package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchControl {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        // Lấy danh sách sản phẩm theo keyword
        List<Product> productList = productDao.searchProduct(keyword);
        // Lấy danh sách category
        List<Category> categoryList = categoryDao.getAllCategories();

        model.addAttribute("product_list", productList);
        model.addAttribute("category_list", categoryList);

        // Trả về view shop.jsp (Spring Boot mặc định tìm /templates/shop.jsp hoặc shop.html)
        return "shop";
    }
}
