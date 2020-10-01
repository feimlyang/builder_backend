package mystore.transaction.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mystore.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    //order_sku *:1 sku
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //orderItems_sku 1:1 product_sku
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_sku", referencedColumnName = "sku")
    private Product product;

    //order 1:* orderDetails
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

    private Double soldPrice;
    private Integer quantity;

    @Override
    public String toString(){
        return product.toString() + ": "  + "$" +soldPrice + " x " + quantity;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(Double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
