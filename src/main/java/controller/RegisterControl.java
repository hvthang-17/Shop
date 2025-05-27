package controller;

import dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class RegisterControl {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Tới register.jsp
    }

    @PostMapping("/register")
    public String registerAccount(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "repeat-password", required = false) String repeatPassword,
            @RequestParam(value = "profile-image", required = false) MultipartFile profileImage,
            Model model
    ) throws IOException {
        System.out.println(">>> username = " + username); // Kiểm tra

        // DEBUG
        System.out.println("▶ [DEBUG] username: " + username);
        System.out.println("▶ [DEBUG] password: " + password);
        System.out.println("▶ [DEBUG] repeat-password: " + repeatPassword);
        System.out.println("▶ [DEBUG] profile-image: " + (profileImage != null ? profileImage.getOriginalFilename() : "null"));

        if (username == null || password == null || repeatPassword == null) {
            model.addAttribute("alert", "<div class='alert alert-danger wrap-input100'>" +
                    "<p style='text-align:center;'>Missing form data!</p></div>");
            return "register";
        }
        // Kiểm tra password khớp
        if (!password.equals(repeatPassword)) {
            model.addAttribute("alert", "<div class=\"alert alert-danger wrap-input100\"><p style=\"font-family: Ubuntu-Bold; font-size: 18px; text-align: center\">Incorrect password!</p></div>");
            return "register";
        }

        // Kiểm tra username trùng
        if (accountDao.checkUsernameExists(username)) {
            model.addAttribute("alert", "<div class=\"alert alert-danger wrap-input100\"><p style=\"font-family: Ubuntu-Bold; font-size: 18px; text-align: center\">Username already exists!</p></div>");
            return "register";
        }

        // Lấy InputStream từ ảnh đại diện
        InputStream inputStream = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            inputStream = profileImage.getInputStream();
        }

        // Tạo tài khoản
        accountDao.createAccount(username, password, inputStream);

        model.addAttribute("alert", "<div class=\"alert alert-success wrap-input100\"><p style=\"font-family: Ubuntu-Bold; font-size: 18px; text-align: center\">Create account successfully!</p></div>");
        return "login"; // Tới login.jsp
    }
}
