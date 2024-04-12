package business.design;

import business.designimpl.CategoryService;

import java.util.Scanner;

public interface IProduct {
    void addProduct(Scanner scanner, CategoryService categoryService);
    void editProduct(Scanner scanner, CategoryService categoryService);

    void hideProductById(Scanner scanner);
    void hideProductsByIds(Scanner scanner);
    void searchProductByName(Scanner scanner);
}
