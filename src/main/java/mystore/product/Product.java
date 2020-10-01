package mystore.product;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"productName"})
})

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sku;

    @NotBlank
    private String productName;

    @NotBlank
    private Double listPrice;

    @NotBlank
    private Long stocks;

    private String productTypeId;
    private String imageUrl;

    @Override
    public String toString(){
        return productName;
    }

    //getters and setters


    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Long getStocks() {
        return stocks;
    }

    public void setStocks(Long stocks) {
        this.stocks = stocks;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
