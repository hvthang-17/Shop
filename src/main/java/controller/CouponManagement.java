package controller;

import dao.CouponDAO;
import model.Account;
import model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class CouponManagement {

    @Autowired
    private CouponDAO couponDAO;

    // Hiển thị trang quản lý mã giảm giá
    @GetMapping("/coupon-management")
    public String couponManagement(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        List<Coupon> couponList = couponDAO.getAllCoupons();
        model.addAttribute("coupon_list", couponList);
        model.addAttribute("coupon_management_active", "active");

        return "coupon-management";
    }

    // Xử lý thêm mã giảm giá
    @PostMapping("/add-coupon")
    public String addCoupon(@RequestParam("code") String code,
                            @RequestParam("discountPercent") int discountPercent,
                            @RequestParam("expiryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date expiryDate,
                            HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        Coupon coupon = new Coupon(0, code, discountPercent, new Date(expiryDate.getTime()));
        couponDAO.addCoupon(coupon);

        return "redirect:/coupon-management";
    }

    // Xử lý cập nhật mã giảm giá (POST)
    @PostMapping("/edit-coupon")
    public String editCoupon(@RequestParam("coupon-id") int id,
                             @RequestParam("coupon-code") String code,
                             @RequestParam("discount-percent") int discountPercent,
                             @RequestParam("expiry-date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date expiryDate,
                             HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        Coupon coupon = new Coupon(id, code, discountPercent, new Date(expiryDate.getTime()));
        couponDAO.updateCoupon(coupon);

        return "redirect:/coupon-management";
    }

    @GetMapping("/delete-coupon")
    public String deleteCoupon(@RequestParam("coupon-id") int id, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        couponDAO.deleteCoupon(id);
        return "redirect:/coupon-management";
    }


    // Hiển thị form chỉnh sửa (trả về trang edit-coupon.jsp)
    @GetMapping("/edit-coupon")
    public String showEditForm(@RequestParam("coupon-id") int id, HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }
        Coupon coupon = couponDAO.getCouponById(id);
        if (coupon == null) {
            return "redirect:/coupon-management";
        }
        model.addAttribute("coupon", coupon);
        return "edit-coupon"; // trả về trang edit-coupon.jsp
    }
}
