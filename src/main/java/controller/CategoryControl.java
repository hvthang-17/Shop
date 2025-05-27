package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryControl {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryControl(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping
    public String getCategoryPage(@RequestParam(name = "category_id", required = false) String categoryIdStr,
                                  Model model,
                                  HttpServletResponse response) throws IOException {
        int categoryId;
        if (categoryIdStr == null) {
            response.sendRedirect("shop.jsp");
            return null;
        }
        try {
            categoryId = Integer.parseInt(categoryIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("shop.jsp");
            return null;
        }

        List<Product> productList = productDao.getAllCategoryProducts(categoryId);
        List<Category> categoryList = categoryDao.getAllCategories();

        model.addAttribute("product_list", productList);
        model.addAttribute("category_list", categoryList);
        
        System.out.println("▶ DEBUG category_list:");
        for (Category category : categoryList) {
            System.out.println(category); // In ra toString() của mỗi Category
        }


        return "shop";
    }

    @PostMapping
    public void handlePost(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(), "POST method is not supported.");
    }
}
