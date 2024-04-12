package business.design;

import business.entity.User;

import java.util.Scanner;

public interface IUser {
    void register(Scanner scanner);
    User login(Scanner scanner);
    void displayUsers();
    void searchUserByName(String name);
    void unblockUser(String username);
    void changePassword(Scanner scanner, User currentUser);
}
