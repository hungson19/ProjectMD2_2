package business.entity;

import business.entity.enumModel.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private long orderId;
    private long userId;
    private String nameReceive;
    private String phoneNumber;
    private String address;
    private double total;
    private OrderStatus orderStatus;
    private List<OrderDetail> orderDetails;
    private LocalDateTime orderAt;
    private LocalDateTime deliverAt;

    public Order() {
    }

    public Order(long orderId, long userId, String nameReceive, String phoneNumber, String address, double total, OrderStatus orderStatus, List<OrderDetail> orderDetails, LocalDateTime orderAt, LocalDateTime deliverAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.nameReceive = nameReceive;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
        this.orderAt = orderAt;
        this.deliverAt = deliverAt;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNameReceive() {
        return nameReceive;
    }

    public void setNameReceive(String nameReceive) {
        this.nameReceive = nameReceive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    public LocalDateTime getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(LocalDateTime deliverAt) {
        this.deliverAt = deliverAt;
    }
}
