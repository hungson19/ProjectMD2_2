package business.design;

import business.designimpl.OrderService;
import business.designimpl.ProductService;
import business.designimpl.UserService;

import java.util.Scanner;

public interface ICart {
    void displayCart(UserService userService);
    void changeQuantity(UserService userService, Scanner scanner, ProductService productService);
    void removeFromCart(UserService userService, Scanner scanner);
    void placeOrder(Scanner scanner, UserService userService, OrderService orderService, ProductService productService);

}
