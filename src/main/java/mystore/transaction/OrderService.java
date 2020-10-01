package mystore.transaction;


import mystore.product.Product;
import mystore.product.ProductRepository;
import mystore.transaction.enums.OrderStatus;
import mystore.transaction.models.*;
import mystore.transaction.repos.OrderItemsRepository;
import mystore.transaction.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service("OrderService")
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    public Order addOrder(Order order) {
        Order newOrder = new Order();
        Set<OrderItem> newOrderItems = new HashSet<>();

        for (Iterator iter = order.getOrderItems().iterator(); iter.hasNext();) {
            OrderItem orderItem = (OrderItem) iter.next();
            OrderItem newItem = new OrderItem();
            Product productInDb = productRepository.findById(orderItem.getProduct().getSku()).orElse(null);
            if (productInDb.getStocks() >= orderItem.getQuantity()) {
                productInDb.setStocks(productInDb.getStocks() - orderItem.getQuantity());
                newItem.setOrder(newOrder);
                newItem.setSoldPrice(orderItem.getSoldPrice());
                newItem.setQuantity(orderItem.getQuantity());
                newItem.setProduct(productInDb);
                orderItemsRepository.save(newItem);
            } else {
                return null;
            }
            newOrderItems.add(newItem);
        }
        newOrder.setOrderItems(newOrderItems);
        newOrder.setEmailAddress(order.getEmailAddress());
        newOrder.setOrderStatus(OrderStatus.Processing);
        newOrder.setPaypalOrderId(order.getPaypalOrderId());
        return orderRepository.save(newOrder);
    }


    public Boolean updateStocks(Order order) {
        Set<OrderItem> orderItems = order.getOrderItems();
        Boolean stockStatus = true;
        for (Iterator iter = orderItems.iterator(); iter.hasNext(); ) {
            OrderItem item = (OrderItem) iter.next();
            if (item.getProduct().getStocks() >= item.getQuantity()) {
                //update stocks in Product for each items
                item.getProduct().setStocks(item.getProduct().getStocks() - item.getQuantity());
            } else {
                stockStatus = false;
                break;
            }
        }
        return stockStatus;
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
