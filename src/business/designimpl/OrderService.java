package business.designimpl;

import business.design.IOrder;
import business.entity.*;
import business.entity.enumModel.OrderStatus;
import utils.IOFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OrderService implements IOrder {
    private Map<Long, Order> orders;

    public OrderService() {
        orders = IOFile.readFromFile(IOFile.ORDER_PATH);
    }

    public void displayOrdersByStatus(OrderStatus orderStatus) {
        System.out.println("Danh sách hóa đơn :" + orderStatus.toString());
        for (Order order : orders.values()) {
            if (order.getOrderStatus() == orderStatus) {
                System.out.print("ID đơn hàng: " + order.getOrderId());
                System.out.print(" ----- Ngày đặt hàng: " + order.getOrderAt());
                System.out.println(" ----- Tổng số tiền: " + order.getTotal());
            }
        }
    }

    public void displayOrders() {
        System.out.println("Danh sách hóa đơn :");
        for (Order order : orders.values()) {
            System.out.print("ID đơn hàng: " + order.getOrderId());
            System.out.print(" ----- Ngày đặt hàng: " + order.getOrderAt());
            System.out.println(" ----- Tổng số tiền: " + order.getTotal());
        }
    }
    public void displayMyOrders(UserService userService) {
        User user = userService.getCurrentUser();
        System.out.println("Danh sách hóa đơn của bạn :");
        for (Order order : orders.values()) {
            if (order.getUserId() == user.getUserId()) {
                System.out.print("ID đơn hàng: " + order.getOrderId());
                System.out.print(" ----- Ngày đặt hàng: " + order.getOrderAt());
                System.out.println(" ----- Tổng số tiền: " + order.getTotal());
            }
        }
    }

    public void confirmOrder(Scanner scanner) {
        displayOrdersByStatus(OrderStatus.UNCONFIRMED);
        System.out.println("Nhập id Oder muốn xắc nhận");
        long orderId = Long.parseLong(scanner.nextLine());

        for (Order order : orders.values()) {
            if (order.getOrderId() == orderId && order.getOrderStatus() == OrderStatus.UNCONFIRMED) {
                order.setOrderStatus(OrderStatus.CONFIRMED);
                System.out.println("Hóa đơn đã được xác nhận.");
                IOFile.writeToFile(IOFile.ORDER_PATH,orders);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn chưa xác nhận có ID là " + orderId);
    }

    public void cancelOrder(Scanner scanner) {
        displayOrdersByStatus(OrderStatus.UNCONFIRMED);
        displayOrdersByStatus(OrderStatus.DELIVERING);
        displayOrdersByStatus(OrderStatus.CONFIRMED);
        System.out.println("Nhập id Oder muốn hủy");
        long orderId = Long.parseLong(scanner.nextLine());

        for (Order order : orders.values()) {
            if (order.getOrderId() == orderId) {
                orders.remove(order.getOrderId());
                System.out.println("Hóa đơn đã được hủy.");
                IOFile.writeToFile(IOFile.ORDER_PATH,orders);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn có ID là " + orderId);
    }

    public void updateDeliveryTime(Scanner scanner) {
        displayOrders();
        System.out.println("Nhập id Oder muốn cập nhật");
        long orderId = Long.parseLong(scanner.nextLine());

        String regex = "\\d{2}-\\d{2}-\\d{4}";
        LocalDateTime inputDateTime;
        while (true) {
            System.out.println("Nhập ngày theo định dạng dd-MM-yyyy:");
            String inputDate = scanner.nextLine();
            if (Pattern.matches(regex, inputDate)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(inputDate, formatter);
                inputDateTime = date.atStartOfDay();
                break;
            } else {
                System.out.println("Định dạng ngày không hợp lệ.");
            }
        }

        for (Order order : orders.values()) {
            if (order.getOrderId() == orderId && order.getOrderStatus() == OrderStatus.DELIVERING) {
                order.setDeliverAt(inputDateTime);
                System.out.println("Thời gian giao hàng đã được cập nhật.");
                IOFile.writeToFile(IOFile.ORDER_PATH,orders);
                return;
            }
        }
        System.out.println("Không tìm thấy hóa đơn đang giao hàng có ID là " + orderId);
    }

    public void createOrder(Scanner scanner, UserService userService, ProductService productService) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Vui lòng đăng nhập trước khi tạo hóa đơn.");
            return;
        }
        System.out.print("Nhập tên người nhận hàng: ");
        String nameReceive = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập địa chỉ giao hàng: ");
        String address = scanner.nextLine();
        OrderStatus orderStatus = OrderStatus.UNCONFIRMED;

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<CartItem> cartItems = currentUser.getCart();
        double total = 0;
        for (CartItem c:cartItems) {
            Product product = productService.findById(c.getProductId());
            OrderDetail orderDetail = new OrderDetail((long) product.getProductId(), product.getProductName(), product.getUnitPrice(), c.getQuantity());
            orderDetails.add(orderDetail);
            total += product.getUnitPrice() * c.getQuantity();
        }
        LocalDateTime orderAt = LocalDateTime.now();
        LocalDateTime deliverAt = orderAt.plusDays(3);;

        Order order = new Order(getMaxId()+1, currentUser.getUserId(), nameReceive, phoneNumber, address, total, orderStatus, orderDetails, orderAt, deliverAt);
        orders.put(order.getOrderId(),order);
        IOFile.writeToFile(IOFile.ORDER_PATH,orders);
    }


    public long getMaxId() {
        long idMax = 0;
        for (Order p : orders.values()) {
            if (idMax < p.getOrderId()) {
                idMax = p.getOrderId();
            }
        }
        return idMax;
    }



}
