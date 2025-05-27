package controller;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutControl {

    @GetMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Xóa session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("account");
            session.invalidate();
        }

        // Xóa cookie username và password
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/"); // cần set path để cookie bị xóa đúng scope
                    response.addCookie(cookie);
                }
            }
        }

        // Redirect về trang chủ
        return "redirect:/";
    }
}
