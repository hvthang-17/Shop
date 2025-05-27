package controller;

import dao.OrderDao;
import dao.ProductDao;
import model.Account;
import model.CartProduct;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderManagementControl {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/order-management")
    public String orderManagement(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            // Nếu chưa đăng nhập thì redirect đến trang login
            return "redirect:/login";
        }

        int accountId = account.getId();

        // Lấy danh sách sản phẩm của seller
        List<Product> productList = productDao.getSellerProducts(accountId);

        List<CartProduct> cartProductList = new ArrayList<>();
        for (Product product : productList) {
            List<CartProduct> sellerOrders = orderDao.getSellerOrderDetail(product.getId());
            if (sellerOrders != null) {
                cartProductList.addAll(sellerOrders);
            }
        }

        model.addAttribute("order_detail_list", cartProductList);
        model.addAttribute("order_management_active", "active");

        // Trả về trang order-management.jsp (đặt trong thư mục views)
        return "order-management";
    }
}
