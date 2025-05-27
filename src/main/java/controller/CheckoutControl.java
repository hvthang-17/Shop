package controller;

import dao.AccountDao;
import dao.OrderDao;
import model.Account;
import model.Order;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutControl {

    private final OrderDao orderDao;
    private final AccountDao accountDao;

    @Autowired
    public CheckoutControl(OrderDao orderDao, AccountDao accountDao) {
        this.orderDao = orderDao;
        this.accountDao = accountDao;
    }

    // Xử lý GET /checkout: hiển thị trang thanh toán (checkout form)
    @GetMapping
    public String showCheckoutForm(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển về trang đăng nhập
            return "redirect:/login.jsp";
        }

        Order order = (Order) session.getAttribute("order");
        Double totalPrice = (Double) session.getAttribute("total_price");

        if (order == null || totalPrice == null) {
            model.addAttribute("error", "Your cart is empty.");
            return "cart"; // Hoặc trang phù hợp hiển thị giỏ hàng
        }

        model.addAttribute("order", order);
        model.addAttribute("total_price", totalPrice);
        model.addAttribute("account", account);

        return "checkout"; // checkout.jsp sẽ hiển thị form thanh toán
    }

    // Xử lý POST /checkout: nhận dữ liệu form và xử lý thanh toán
    @PostMapping
    public String doCheckout(
            String firstName,
            String lastName,
            String address,
            String email,
            String phone,
            HttpSession session,
            Model model
    ) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login.jsp";
        }

        Double totalPrice = (Double) session.getAttribute("total_price");
        Order order = (Order) session.getAttribute("order");

        if (totalPrice == null || order == null) {
            model.addAttribute("error", "Your cart is empty or total price is missing.");
            return "cart";
        }

        // Cập nhật thông tin người dùng
        accountDao.updateProfileInformation(account.getId(), firstName, lastName, address, email, phone);

        // Tạo đơn hàng trong DB
        orderDao.createOrder(account.getId(), totalPrice, order.getCartProducts());

        // Xóa session giỏ hàng
        session.removeAttribute("order");
        session.removeAttribute("total_price");

        return "thankyou"; // thankyou.jsp trang cảm ơn
    }
}
