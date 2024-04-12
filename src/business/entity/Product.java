package business.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productId;
    private String productName;
    private String categoryId;
    private String description;
    private double unitPrice;
    private int stock;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean status;

    public Product() {
    }

    public Product(int productId, String productName, String categoryId, String description, double unitPrice, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();;
        this.status = true;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }

    public boolean isHotProduct() {
        double hotPriceThreshold = 1000.0;
        return this.unitPrice > hotPriceThreshold;
    }

}