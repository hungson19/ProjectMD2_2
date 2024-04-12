package business.design;

import business.designimpl.ProductService;
import business.designimpl.UserService;
import business.entity.enumModel.OrderStatus;

import java.util.Scanner;

public interface IOrder {
    void displayOrdersByStatus(OrderStatus orderStatus);
    void displayOrders();
    void displayMyOrders(UserService userService);
    void confirmOrder(Scanner scanner);
    void cancelOrder(Scanner scanner);
    void updateDeliveryTime(Scanner scanner);
    void createOrder(Scanner scanner, UserService userService, ProductService productService);
}
