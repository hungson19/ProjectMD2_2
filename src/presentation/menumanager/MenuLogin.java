package presentation.menumanager;

import business.entity.User;
import business.entity.enumModel.Role;
import business.designimpl.*;

import java.util.Scanner;

public class MenuLogin {
    public static void userManagementMenu(OrderService orderService, Scanner scanner, UserService userService, CategoryService categoryService, ProductService productService, CartService cartService) {
        while (true) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    userService.register(scanner);
                    break;
                case 2:
                    User user = userService.login(scanner);
                    if (user != null) {
                        userService.setCurrentUser(user);
                        if (user.getRole() == Role.ROLE_ADMIN) {
                            MenuAdmin.menu(orderService,scanner, userService, categoryService, productService);
                        } else {
                            MenuUser.displayMainMenu(orderService,user, userService, productService, categoryService, cartService);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
