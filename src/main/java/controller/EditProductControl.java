package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/edit-product")
public class EditProductControl {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    @Autowired
    public EditProductControl(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    // Xử lý GET để hiển thị form chỉnh sửa sản phẩm
    @GetMapping
    public String showEditProduct(@RequestParam("product-id") int productId, Model model) {
        Product product = productDao.getProduct(productId);
        List<Category> categoryList = categoryDao.getAllCategories();

        model.addAttribute("product", product);
        model.addAttribute("category_list", categoryList);
        return "edit-product"; // sẽ được resolve thành edit-product.jsp
    }

    // Xử lý POST để cập nhật sản phẩm
    @PostMapping
    public String editProduct(
            @RequestParam("product-id") int productId,
            @RequestParam("product-name") String productName,
            @RequestParam("product-price") double productPrice,
            @RequestParam("product-description") String productDescription,
            @RequestParam("product-category") int productCategory,
            @RequestParam("product-amount") int productAmount,
            @RequestParam(value = "product-image", required = false) MultipartFile productImage
    ) throws IOException {
        InputStream inputStream = null;
        if (productImage != null && !productImage.isEmpty()) {
            inputStream = productImage.getInputStream();
        }
        productDao.editProduct(productId, productName, inputStream, productPrice, productDescription, productCategory, productAmount);
        return "redirect:/product-management";
    }
}
