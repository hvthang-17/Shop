package controller;

import dao.AccountDao;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.*;

@Controller
@RequestMapping("/login")
public class LoginControl {

    private final AccountDao accountDao;

    @Autowired
    public LoginControl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private Account getAccountFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = "";
        String password = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    try {
                        username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        username = cookie.getValue();
                    }
                }
                if ("password".equals(cookie.getName())) {
                    try {
                        password = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        password = cookie.getValue();
                    }
                }
            }
        }
        return accountDao.checkLoginAccount(username, password);
    }

    private void setLoginCookies(HttpServletResponse response, Account account, boolean rememberMe) {
        if (rememberMe) {
            try {
                Cookie usernameCookie = new Cookie("username", URLEncoder.encode(account.getUsername(), "UTF-8"));
                usernameCookie.setMaxAge(600); // 10 minutes
                response.addCookie(usernameCookie);

                Cookie passwordCookie = new Cookie("password", URLEncoder.encode(account.getPassword(), "UTF-8"));
                passwordCookie.setMaxAge(600);
                response.addCookie(passwordCookie);
            } catch (UnsupportedEncodingException e) {
                // ignore encoding exception
            }
        }
    }

    private void executeLogin(HttpServletRequest request, HttpServletResponse response, Account account, boolean rememberMe) {
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        setLoginCookies(response, account, rememberMe);
    }

    @GetMapping
    public String showLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = getAccountFromCookies(request);
        if (account != null) {
            executeLogin(request, response, account, true);
            return "redirect:/"; // login thành công, chuyển đến trang chủ
        }
        return "login"; // trả về login.jsp
    }

    @PostMapping
    public String doLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember-me-checkbox", required = false) String rememberMeCheckbox,
            @RequestParam(value = "status", required = false) String status
    ) {
        boolean rememberMe = (rememberMeCheckbox != null);
        Account account = accountDao.checkLoginAccount(username, password);

        if (account == null && "typed".equals(status)) {
            String alert = """
                    <div class="alert alert-danger wrap-input100">
                        <p style="font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center">
                            Wrong username or password!
                        </p>
                    </div>
                    """;
            model.addAttribute("alert", alert);
            return "login"; // trở lại trang login.jsp với thông báo lỗi
        } else if (account == null) {
            // chưa nhập hoặc lần đầu vào login page
            return "login";
        } else {
            executeLogin(request, response, account, rememberMe);
            return "redirect:/"; // login thành công chuyển trang chủ
        }
    }
}
