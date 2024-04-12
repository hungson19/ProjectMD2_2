package presentation.menumanager;

import business.designimpl.UserService;

import java.util.Scanner;

public class MenuManagerAccount {
    public static void menu(Scanner scanner, UserService userService) {
        while (true) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("--- Quản lý người dùng ---");
            System.out.println("1. Hiển thị danh sách người dùng");
            System.out.println("2. Tìm kiếm người dùng theo tên");
            System.out.println("3. Block tài khoản người dùng");
            System.out.println("4. Unblock tài khoản người dùng");
            System.out.println("5. Quay lại menu chính");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    userService.displayUsers();
                    break;
                case 2:
                    System.out.print("Nhập tên người dùng cần tìm: ");
                    String name = scanner.nextLine();
                    userService.searchUserByName(name);
                    break;
                case 3:
                    System.out.print("Nhập id người dùng cần block: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    userService.blockUser(id);
                    break;
                case 4:
                    System.out.print("Nhập tên người dùng cần unblock: ");
                    String userToUnblock = scanner.nextLine();
                    userService.unblockUser(userToUnblock);
                    break;
                case 5:
                    return; // Quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
