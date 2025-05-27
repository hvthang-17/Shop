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
public class ShopControl {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/shop")
    public String shop(
        @RequestParam(value = "index", defaultValue = "1") int index,
        Model model) {

        // Lấy 12 sản phẩm cho trang hiện tại
        List<Product> productList = productDao.get12ProductsOfPage(index);

        // Lấy tất cả category
        List<Category> categoryList = categoryDao.getAllCategories();

        // Tính tổng số trang
        int totalProduct = productDao.getTotalNumberOfProducts();
        int totalPages = totalProduct / 12;
        if (totalProduct % 12 != 0) {
            totalPages++;
        }

        String active = "active";

        model.addAttribute("product_list", productList);
        model.addAttribute("category_list", categoryList);
        model.addAttribute("total_pages", totalPages);
        model.addAttribute("shop_active", active);
        model.addAttribute("page_active", index);

        // Trả về view shop.jsp hoặc shop.html
        return "shop";
    }
}
