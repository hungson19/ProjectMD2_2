package presentation.menumanager;

import business.designimpl.CategoryService;

import java.util.Scanner;

public class MenuManagerCategory {
    public static void menu(Scanner scanner, CategoryService categoryService){
        while (true) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("--- Quản lý Category ---");
            System.out.println("1. Hiển thị danh sách danh mục");
            System.out.println("2. Tạo mới danh mục");
            System.out.println("3. Chỉnh sửa thông tin danh mục");
            System.out.println("4. Ẩn danh mục theo mã danh mục");
            System.out.println("5. Ẩn nhiều danh mục trong danh sách nhập vào");
            System.out.println("6. Tìm kiếm danh mục theo tên");
            System.out.println("7. Quay lại menu chính");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    categoryService.displayCategories();
                    break;
                case 2:
                    categoryService.addCategory(scanner);
                    break;
                case 3:
                    categoryService.editCategory(scanner);
                    break;
                case 4:
                    categoryService.hideCategoryById(scanner);
                    break;
                case 5:
                    categoryService.hideCategoriesByIds(scanner);
                    break;
                case 6:
                    categoryService.searchCategoryByName(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
