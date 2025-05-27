package controller;

import dao.AccountDao;
import model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ProfileControl {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/profile-page")
    public String showProfilePage() {
        // Chỉ đơn giản trả về trang profile-page.jsp
        return "profile-page";
    }

    @PostMapping("/profile-page")
    public String updateProfile(
            HttpSession session,
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("profile-image") MultipartFile profileImage
    ) throws IOException {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // Nếu chưa đăng nhập, chuyển về trang login
            return "redirect:/login";
        }

        int accountId = account.getId();

        InputStream inputStream = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            inputStream = profileImage.getInputStream();
        }

        // Debug log
        System.out.println(accountId + " " + firstName + " " + lastName + " " + address + " " + email + " " + phone);

        // Gọi hàm cập nhật profile trong DAO
        accountDao.editProfileInformation(accountId, firstName, lastName, address, email, phone, inputStream);

        // Sau khi cập nhật, redirect về login hoặc trang khác tuỳ mục đích
        return "redirect:/login";
    }
}
