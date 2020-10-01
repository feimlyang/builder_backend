package mystore.mail;

import mystore.transaction.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) throws MailException{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@mystore.ca");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public String formatOrderText(Order order){
        System.out.println(order.getOrderItems().toString());
        return "Order reference number: " + order.getId() + "\n"
                + "Order placed at: " + order.getOrderDate() + "\n"
                + "Order items: " + order.getOrderItems().toString() + "\n";
    }
}
