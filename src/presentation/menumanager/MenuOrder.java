package presentation.menumanager;

import business.entity.enumModel.OrderStatus;
import business.designimpl.OrderService;

import java.util.Scanner;

public class MenuOrder {
    public static void displayOrderMenu(OrderService orderService, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("---- Menu Hóa Đơn ----");
            System.out.println("1. Hiển thị danh sách hóa đơn chưa xác nhận");
            System.out.println("2. Hiển thị danh sách hóa đơn đã xác nhận");
            System.out.println("3. Hiển thị danh sách hóa đơn đã giao hàng");
            System.out.println("4. Hiển thị danh sách hóa đơn trả hàng");
            System.out.println("5. Xác nhận hóa đơn đang chờ");
            System.out.println("6. Hủy 1 đơn hàng");
            System.out.println("7. Cập nhật đơn hàng đang giao");
            System.out.println("8. Quay lại Menu chính");
            System.out.print("Chọn chức năng (1-8): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    orderService.displayOrdersByStatus(OrderStatus.UNCONFIRMED);
                    break;
                case 2:
                    orderService.displayOrdersByStatus(OrderStatus.CONFIRMED);
                    break;
                case 3:
                    orderService.displayOrdersByStatus(OrderStatus.DELIVERING);
                    break;
                case 4:
                    orderService.displayOrdersByStatus(OrderStatus.RETURNED);
                    break;
                case 5:
                    orderService.confirmOrder(scanner);
                    break;
                case 6:
                    orderService.cancelOrder(scanner);
                    break;
                case 7:
                    orderService.updateDeliveryTime(scanner);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Vui lòng chọn một lựa chọn từ 1 đến 8.");
            }
        }
    }
}
