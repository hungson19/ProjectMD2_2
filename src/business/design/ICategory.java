package business.design;

import java.util.Scanner;

public interface ICategory {
    void displayCategories();
    void addCategory(Scanner scanner);
    void editCategory(Scanner scanner);
    void hideCategoryById(Scanner scanner);
    void hideCategoriesByIds(Scanner scanner);
    void searchCategoryByName(Scanner scanner);
}
