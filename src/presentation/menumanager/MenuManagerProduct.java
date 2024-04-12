package presentation.menumanager;

import business.designimpl.CategoryService;
import business.designimpl.ProductService;

import java.util.Scanner;

public class MenuManagerProduct {
    public static void menu(Scanner scanner, ProductService productService, CategoryService categoryService){
        while (true) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("--- Quản lý sản phẩm ---");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Chỉnh sửa thông tin sản phẩm");
            System.out.println("4. Ẩn sản phẩm theo mã sản phẩm");
            System.out.println("5. Ẩn nhiều sản phẩm trong danh sách nhập vào");
            System.out.println("6. Tìm kiếm sản phẩm theo tên");
            System.out.println("7. Quay lại menu chính");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    productService.displayProducts(categoryService.getCategories());
                    break;
                case 2:
                    productService.addProduct(scanner, categoryService);
                    break;
                case 3:
                    productService.editProduct(scanner,categoryService);
                    break;
                case 4:
                    productService.hideProductById(scanner);
                    break;
                case 5:
                    productService.hideProductsByIds(scanner);
                    break;
                case 6:
                    productService.searchProductByName(scanner);
                    break;
                case 7:
                   return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
