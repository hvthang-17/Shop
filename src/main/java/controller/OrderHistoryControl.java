package controller;

import dao.OrderDao;
import model.Account;
import model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderHistoryControl {

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/order-history")
    public String orderHistory(HttpSession session, Model model) {
        // Lấy account từ session
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            // Nếu chưa đăng nhập, có thể redirect về trang login hoặc trang khác
            return "redirect:/login";
        }

        // Lấy danh sách đơn hàng theo account id
        List<Order> orderList = orderDao.getOrderHistory(account.getId());

        // Đưa dữ liệu vào model gửi tới view
        model.addAttribute("order_list", orderList);
        model.addAttribute("order_history_active", "active");

        // Trả về view tên order-history.jsp
        return "order-history";
    }
}
