package controller;

import dao.AccountDao;
import dao.OrderDao;
import dao.CouponDAO;
import model.Account;
import model.Order;
import model.Coupon;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutControl {

    private final OrderDao orderDao;
    private final AccountDao accountDao;
    private final CouponDAO couponDao;

    @Autowired
    public CheckoutControl(OrderDao orderDao, AccountDao accountDao, CouponDAO couponDao) {
        this.orderDao = orderDao;
        this.accountDao = accountDao;
        this.couponDao = couponDao;
    }

    @GetMapping
    public String showCheckoutForm(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login.jsp";
        }

        Order order = (Order) session.getAttribute("order");
        Double totalPrice = (Double) session.getAttribute("total_price");
        Coupon discountCoupon = (Coupon) session.getAttribute("discountCoupon");

        if (order == null || totalPrice == null) {
            model.addAttribute("error", "Your cart is empty.");
            return "cart";
        }

        model.addAttribute("order", order);
        model.addAttribute("total_price", totalPrice);
        model.addAttribute("discountCoupon", discountCoupon);
        model.addAttribute("account", account);

        return "checkout";
    }

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

        accountDao.updateProfileInformation(account.getId(), firstName, lastName, address, email, phone);
        orderDao.createOrder(account.getId(), totalPrice, order.getCartProducts());

        session.removeAttribute("order");
        session.removeAttribute("total_price");
        session.removeAttribute("discountCoupon");

        return "thankyou";
    }

    // Xử lý áp dụng mã giảm giá
    @PostMapping("/apply-coupon")
    public String applyCoupon(
            @RequestParam("coupon") String couponCode,
            HttpSession session,
            Model model
    ) {
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            model.addAttribute("error", "Giỏ hàng của bạn đang trống.");
            return "cart";
        }

        double totalPrice = order.calculateTotal();
        Coupon coupon = couponDao.getCouponByCode(couponCode.trim());

        if (coupon != null && coupon.getExpiryDate().after(new java.util.Date())) {
            double discountedPrice = totalPrice * (100 - coupon.getDiscountPercent()) / 100.0;

            session.setAttribute("discountCoupon", coupon);
            session.setAttribute("discountedPrice", discountedPrice);
            session.setAttribute("total_price", discountedPrice);

            model.addAttribute("discountCoupon", coupon);
            model.addAttribute("discountedPrice", discountedPrice);
        } else {
            model.addAttribute("error", "Mã giảm giá không hợp lệ hoặc đã hết hạn.");
            session.setAttribute("total_price", totalPrice); // Không giảm giá
        }

        model.addAttribute("order", order);
        model.addAttribute("total_price", session.getAttribute("total_price"));
        return "cart";
    }
}
