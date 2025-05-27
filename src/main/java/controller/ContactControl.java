package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import dao.ContactDAO;
import model.Account;
import model.Contact;
import util.MailUtil;

@Controller
public class ContactControl {
	
	@Autowired
    private ContactDAO contactDAO;

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact"; 
    }

    @PostMapping("/contact")
    public String submitContactForm(
            @RequestParam("c_fname") String fname,
            @RequestParam("c_lname") String lname,
            @RequestParam("c_email") String email,
            @RequestParam("c_subject") String subject,
            @RequestParam("c_message") String message,
            Model model) {

    	Contact contact = new Contact(fname, lname, email, subject, message);
        boolean inserted = contactDAO.insertContact(contact);
        boolean mailSent = false;

        try {
            MailUtil.sendEmail(
                "hvthang1705@gmail.com",
                "Liên hệ mới từ " + fname + " " + lname + " - " + subject,
                "Email: " + email + "\n\nNội dung:\n" + message
            );
            mailSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (inserted && mailSent) {
            model.addAttribute("success", "Cảm ơn bạn đã liên hệ! Chúng tôi sẽ phản hồi sớm.");
        } else {
            model.addAttribute("error", "Có lỗi xảy ra khi gửi thông tin. Vui lòng thử lại.");
        }

        return "contact";
    }
    

    @GetMapping("/contact-management")
    public String contactManagement(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        List<Contact> contactList = contactDAO.getAllContacts();
        model.addAttribute("contact_list", contactList);
        model.addAttribute("contact_management_active", "active");

        return "contact-management";
    }

    @GetMapping("/delete-contact")
    public String deleteContact(@RequestParam("id") int id, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        contactDAO.deleteContact(id);
        return "redirect:/contact-management";
    }
}
