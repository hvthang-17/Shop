package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailUtil {

    public static void sendEmail(String toEmail, String subject, String messageText) throws MessagingException {
        final String fromEmail = "hoangthang050517@gmail.com"; 
        final String password = "aocs arvh ghrg oeld";    

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo email
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromEmail));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        msg.setSubject(subject);
        msg.setText(messageText);

        Transport.send(msg);
    }
}

