package business.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long productId;
    private String name;
    private double unitPrice;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(Long productId, String name, double unitPrice, int quantity) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
