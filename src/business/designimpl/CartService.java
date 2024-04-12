package business.designimpl;

import business.design.ICart;
import business.entity.CartItem;
import business.entity.Product;
import business.entity.User;
import utils.IOFile;

import java.util.*;

public class CartService implements ICart {

    public void addToCart(UserService userService, Product product, int quantity) {
        User user = userService.getCurrentUser();
        List<CartItem> cartItems = user.getCart();

        int indexCart = getCartInList(cartItems, product);
        if (indexCart != -1) {
            CartItem cartItem = cartItems.get(indexCart);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem((long) product.getProductId(), quantity);
            cartItems.add(cartItem);
        }
        IOFile.writeToFile(IOFile.USER_PATH, UserService.getUsers());
    }

    public int getCartInList(List<CartItem> cartItems, Product product) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProductId() == product.getProductId()) {
                return i;
            }
        }
        return -1;
    }

    public void displayCart(UserService userService) {
        List<CartItem> cartItems = userService.getCurrentUser().getCart();
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (CartItem c : cartItems) {
                System.out.println("Sản phẩm: " + c.getProductId() + " - Số lượng: " + c.getQuantity());
            }
        }
    }

    public void changeQuantity(UserService userService, Scanner scanner, ProductService productService) {
        List<CartItem> cartItems = userService.getCurrentUser().getCart();
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }
        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        int index = 1;
        for (CartItem c : cartItems) {
            System.out.println(index + ". " + "Sản phẩm: " + c.getProductId() + " - Số lượng: " + c.getQuantity());
            index++;
        }

        System.out.print("Chọn số thứ tự của sản phẩm cần thay đổi số lượng: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > cartItems.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }
        CartItem cartItem = cartItems.get(choice);
        Product product = productService.findById(Math.toIntExact(cartItem.getProductId()));
        int quantity;
        while (true) {
            try {
                System.out.print("Nhập số lượng: ");
                System.out.println("Số lượng sản phẩm hiện có: " + product.getStock());
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0 || quantity > product.getStock()) {
                    System.out.println("Số lượng không hợp lệ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("sai định dạng");
            }

        }
        cartItem.setQuantity(quantity);
        IOFile.writeToFile(IOFile.USER_PATH, UserService.getUsers());
        System.out.println("Số lượng của sản phẩm đã được cập nhật trong giỏ hàng.");
    }

    public void removeFromCart(UserService userService, Scanner scanner) {
        List<CartItem> cartItems = userService.getCurrentUser().getCart();
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }
        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        int index = 1;
        for (CartItem c : cartItems) {
            System.out.println(index + ". " + "Sản phẩm: " + c.getProductId() + " - Số lượng: " + c.getQuantity());
            index++;
        }
        System.out.print("Chọn số thứ tự của sản phẩm cần xóa: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > cartItems.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }
        cartItems.remove(choice);
        IOFile.writeToFile(IOFile.USER_PATH, UserService.getUsers());
        System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
    }

    public void placeOrder(Scanner scanner, UserService userService, OrderService orderService, ProductService productService) {
        orderService.createOrder(scanner, userService, productService);
        userService.getCurrentUser().setCart(new ArrayList<>());
        IOFile.writeToFile(IOFile.USER_PATH, UserService.getUsers());
        System.out.println("Mua hàng thành công, đợi xác nhận");
    }
}

