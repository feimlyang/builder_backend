package mystore.transaction.web;

import mystore.core.CoreResponseBody;
import mystore.mail.MailService;
import mystore.transaction.OrderService;
import mystore.transaction.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mystore")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    MailService mailService;

    @PostMapping("/processOrder")
    @CrossOrigin("*")
    public ResponseEntity<CoreResponseBody> processOrder(@RequestBody Order order) {
        CoreResponseBody res;
        Order newOrder = orderService.addOrder(order);
        if (newOrder == null) {
            res = new CoreResponseBody(null, "order denied", new Exception("order duplicates or lack of stocks."));
        } else {
            //send mail
            try{
                String tempUsername = "Customer";
                mailService.sendSimpleMessage(tempUsername, "Order: " + newOrder.getId(), mailService.formatOrderText(newOrder));
            }
            catch (MailException e){
                res = new CoreResponseBody(null, "email send failed", e);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            res = new CoreResponseBody(newOrder, "order has been added and confirmation email sent.", null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/order/{orderId}")
    @CrossOrigin("*")
    public ResponseEntity<CoreResponseBody> findOrderById(@PathVariable Long orderId) {
        CoreResponseBody res;
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            res = new CoreResponseBody(null, "order not found.", new Exception("order not found."));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        res = new CoreResponseBody(order, "get order by id.", null);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
