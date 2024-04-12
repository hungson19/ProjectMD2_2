package presentation.menumanager;

import business.entity.User;
import business.designimpl.*;

import java.util.Scanner;

public class MenuUser {
    public static void displayMainMenu(OrderService orderService, User user, UserService userService, ProductService productService, CategoryService categoryService, CartService cartService) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("\n===== MENU USER =====");
            System.out.println("1. Tìm kiếm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm nổi bật");
            System.out.println("3. Hiển thị sản phẩm theo danh mục");
            System.out.println("4. Danh sách sản phẩm");
            System.out.println("5. Hiển thị sản phẩm theo giá");
            System.out.println("6. Thêm vào giỏ hàng");
            System.out.println("7. Quản lý giỏ hàng");
            System.out.println("8. Hiển thi thông tin cá nhân");
            System.out.println("9. Chỉnh sửa thông tin cá nhân");
            System.out.println("10. Đổi mật khẩu");
            System.out.println("11. Lịch sử mua hàng của ban");
            System.out.println("12. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    productService.searchProductByName(scanner);
                    break;
                case 2:
                    productService.displayHotProducts();
                    break;
                case 3:
                    productService.displayProductsByCategory(scanner, categoryService);
                    break;
                case 4:
                    productService.displayProducts(categoryService.getCategories());
                    break;
                case 5:
                    displaySortProductMenu(scanner, productService);
                    break;
                case 6:
                    productService.addToCart(userService,scanner, cartService);
                    break;
                case 7:
                    CartMenu.displayCartMenu(orderService,productService,userService,scanner, cartService);
                case 8:
                    userService.displayUserProfile(user);
                    break;
                case 9:
                    userService.editUserProfile(scanner, user);
                    break;
                case 10:
                    userService.changePassword(scanner, user);
                    break;
                case 11:
                    orderService.displayMyOrders(userService);
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
        scanner.close();
    }

    private static void displaySortProductMenu(Scanner scanner, ProductService productService) {
        System.out.println("===== MENU SẮP XẾP SẢN PHẨM =====");
        System.out.println("1. Sắp xếp theo giá tăng dần");
        System.out.println("2. Sắp xếp theo giá giảm dần");
        System.out.print("Nhập lựa chọn của bạn: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                productService.displayProductsSortedByPrice(true);
                break;
            case 2:
                productService.displayProductsSortedByPrice(false);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
        }
    }
}


