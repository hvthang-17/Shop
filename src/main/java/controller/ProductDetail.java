package controller;

import dao.ProductDao;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductDetail {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/product-detail")
    public String productDetail(@RequestParam("id") int id,
                                @RequestParam(value = "invalid-quantity", required = false) String invalidQuantity,
                                Model model) {
        // Kiểm tra alert
        boolean alert = (invalidQuantity != null);

        // Lấy sản phẩm theo id
        Product product = productDao.getProduct(id);

        // Kiểm tra số lượng sản phẩm để disable button nếu hết hàng
        String disabled = "";
        if (product.getAmount() <= 0) {
            disabled = "disabled";
        }

        // Lấy tất cả sản phẩm cho phần feature
        List<Product> productList = productDao.getAllProducts();

        // Thiết lập các attribute cho view
        model.addAttribute("alert", alert);
        model.addAttribute("disabled", disabled);
        model.addAttribute("shop_active", "active");
        model.addAttribute("product", product);
        model.addAttribute("product_list", productList);

        // Trả về tên view product-detail.jsp (đặt trong thư mục views)
        return "product-detail";
    }
}
