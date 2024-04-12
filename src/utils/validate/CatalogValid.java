package utils.validate;

import business.entity.Catalog;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogValid {

    public static String catalogId(Scanner scanner, Collection<Catalog> catalogs) {
        while (true) {
            boolean check = true;
            System.out.print("Nhập id danh mục mới:(Bắt đầu bằng kí tự C , 4 kí tự ,ví dụ định dạng C001) ");
            String categoryId = scanner.nextLine();
            if (isValidCatalogId(categoryId)) {
                for (Catalog c : catalogs) {
                    if (c.getCatalogId().equals(categoryId)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    return categoryId;
                }else {
                    System.out.println("id bị trùng");
                }
            } else {
                System.out.println("id sai định dạng");
            }
        }
    }

    public static String catalogName(Scanner scanner, Collection<Catalog> catalogs) {
        while (true) {
            boolean check = true;
            System.out.print("Nhập tên danh mục mới: ");
            String name = scanner.nextLine();
            if (isValidCatalogName(name)) {
                for (Catalog c : catalogs) {
                    if (c.getCatalogName().equals(name)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    return name;
                }else {
                    System.out.println("name bị trùng");
                }
            } else {
                System.out.println("name sai định dạng");
            }
        }
    }

    public static boolean isValidCatalogId(String catalogId) {
        String regex = "^C\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(catalogId);
        return matcher.matches();
    }

    public static boolean isValidCatalogName(String catalogName) {
        if (catalogName.isEmpty()) {
            return false;
        }
        return true;
    }
}
