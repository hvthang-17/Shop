package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Account;
import model.Category;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductManagementControl {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/product-management")
    public String productManagement(HttpSession session, Model model) {
        // Lấy account từ session
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // Nếu chưa đăng nhập thì redirect về login (cách khác có thể xử lý qua filter)
            return "redirect:/login";
        }

        int sellerId = account.getId();

        // Lấy danh sách sản phẩm của seller
        List<Product> productList = productDao.getSellerProducts(sellerId);

        // Lấy danh sách category
        List<Category> categoryList = categoryDao.getAllCategories();

        // Đẩy dữ liệu sang view
        model.addAttribute("category_list", categoryList);
        model.addAttribute("product_list", productList);
        model.addAttribute("product_management_active", "active");

        // Trả về view product-management.jsp
        return "product-management";
    }
}
