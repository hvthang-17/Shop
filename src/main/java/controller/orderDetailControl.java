package controller;

import dao.OrderDao;
import model.CartProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class orderDetailControl {

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/order-detail")
    public String orderDetail(@RequestParam("order-id") int orderId, Model model) {
        // Lấy danh sách chi tiết đơn hàng từ database
        List<CartProduct> list = orderDao.getOrderDetailHistory(orderId);

        // Đưa dữ liệu vào model để gửi đến view
        model.addAttribute("order_detail_list", list);

        // Trả về tên JSP (thường cấu hình ViewResolver tự động thêm .jsp)
        return "order-detail";
    }
}
