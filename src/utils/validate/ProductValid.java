package utils.validate;

import business.entity.Product;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ProductValid {
    public static String validName(Collection<Product> products, Scanner scanner) {
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        while (true) {
            if (!name.equals("")) {
                for (Product p : products) {
                    if (p.getProductName().equals(name)) {
                        System.out.println("Trùng name");
                        continue;
                    }
                }
                return name;
            } else {
                System.out.println("Không rỗng");
            }
        }
    }

    public static double validPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                double unitPrice = Double.parseDouble(scanner.nextLine());
                if (unitPrice > 0) {
                    return unitPrice;
                }else {
                System.err.println("input > 0");
            }
            } catch (Exception e) {
                System.err.println("nhập sai định dạng");
            }
        }
    }

    public static int validStock(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Chọn số lượng của sản phẩm: ");
                int stock = Integer.parseInt(scanner.nextLine());
                if (stock > 0) {
                    return stock;
                }else {
                    System.err.println("input > 0");
                }
            } catch (Exception e) {
                System.err.println("nhập sai định dạng");
            }
        }
    }

    public static String validDesc(Scanner scanner) {
        while (true) {
            System.out.print("Nhập description sản phẩm: ");
            String description = scanner.nextLine();
            if (!description.equals("")){
                return description;
            }else {
                System.out.println("Không rỗng");
            }
        }
    }
}
