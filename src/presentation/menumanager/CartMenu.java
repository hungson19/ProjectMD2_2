package presentation.menumanager;

import business.designimpl.CartService;
import business.designimpl.OrderService;
import business.designimpl.ProductService;
import business.designimpl.UserService;

import java.util.Scanner;

public class CartMenu {
    public static void displayCartMenu(OrderService orderService, ProductService productService, UserService userService, Scanner scanner, CartService cartService) {
        int choice;
        do {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("\n===== CART MENU =====");
            System.out.println("1. Hiển thị danh sách sản phẩm trong giỏ hàng");
            System.out.println("2. Thay đổi số lượng đặt hàng");
            System.out.println("3. Xóa sản phẩm trong giỏ hàng");
            System.out.println("4. Đặt hàng");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    cartService.displayCart(userService);
                    break;
                case 2:
                    cartService.changeQuantity(userService, scanner, productService);
                    break;
                case 3:
                    cartService.removeFromCart(userService, scanner);
                    break;
                case 4:
                    cartService.placeOrder(scanner, userService, orderService, productService);
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
}
