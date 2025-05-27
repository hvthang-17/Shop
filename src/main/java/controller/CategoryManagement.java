package controller;

import dao.CategoryDao;
import model.Account;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryManagement {
    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/category-management")
    public String categoryManagement(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        List<Category> categoryList = categoryDao.getAllCategories();

        model.addAttribute("category_list", categoryList);
        model.addAttribute("product_management_active", "active"); 

        return "category-management";
    }
    
 // Xử lý thêm danh mục
    @PostMapping("/add-category")
    public String addCategory(@RequestParam("name") String name, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        Category category = new Category();
        category.setName(name);
        categoryDao.insertCategory(category);

        return "redirect:/category-management";
    }

    // Xử lý sửa danh mục
    @PostMapping("/edit-category")
    public String editCategory(@RequestParam("id") int id,
                               @RequestParam("name") String name,
                               HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryDao.updateCategory(category);

        return "redirect:/category-management";
    }

    // Xử lý xóa danh mục
    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") int id, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        categoryDao.deleteCategory(id);
        return "redirect:/category-management";
    }
}
