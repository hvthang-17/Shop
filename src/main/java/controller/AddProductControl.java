package controller;

import dao.ProductDao;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AddProductControl {

    private final ProductDao productDao;

    @Autowired
    public AddProductControl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping("/add-product")
    public String addProduct(
            @RequestParam("product-name") String productName,
            @RequestParam("product-price") double productPrice,
            @RequestParam("product-description") String productDescription,
            @RequestParam("product-category") int productCategory,
            @RequestParam("product-amount") int productAmount,
            @RequestParam("product-image") MultipartFile productImage,
            HttpSession session) throws IOException {

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }
        int sellerId = account.getId();

        InputStream inputStream = null;
        if (productImage != null && !productImage.isEmpty()) {
            inputStream = productImage.getInputStream();
        }

        productDao.addProduct(productName, inputStream, productPrice, productDescription, productCategory, sellerId, productAmount);

        return "redirect:/product-management";
    }
}
