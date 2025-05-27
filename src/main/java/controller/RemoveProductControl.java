package controller;

import dao.ProductDao;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RemoveProductControl {

    @Autowired
    private ProductDao productDao;

    // Xử lý GET request xóa sản phẩm theo productId
    @GetMapping("/remove-product")
    public String removeProduct(@RequestParam("product-id") int productId) {
        Product product = productDao.getProduct(productId);
        if (product != null) {
            productDao.removeProduct(productId);
        }
        // Chuyển hướng về trang quản lý sản phẩm
        return "redirect:/product-management";
    }
}
