package utils.validate;

import business.entity.Product;
import business.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValid {
    public static String username(Scanner scanner, Map<String, User> users) {
        while (true) {
            System.out.print("Nhập tên người dùng: (tối thiểu 6 kí tự , ko có kí tự đặc biệt , ko để trống, ko trùng lặp) \n");
            String username = scanner.nextLine();
            if (users.get(username) == null && isValidUsername(username)) {
                return username;
            } else {
                System.out.println("Username đã tồn tại hoặc sai định dạng");
            }
        }
    }

    public static String password(Scanner scanner) {
        while (true) {
            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();
            if (isValidPassword(password)) {
                return password;
            } else {
                System.out.println("password sai định dạng");
            }
        }
    }

    public static String firstName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên: ");
            String firstName = scanner.nextLine();
            if (!firstName.equals("")) {
                return firstName;
            } else {
                System.out.println("firstName rỗng");
            }
        }
    }

    public static String lastName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập họ: ");
            String lastName = scanner.nextLine();
            if (!lastName.equals("")) {
                return lastName;
            } else {
                System.out.println("firstName rỗng");
            }
        }
    }

    public static String email(Scanner scanner, Collection<User> users) {
        while (true) {
            boolean check = true;
            System.out.print("Nhập email: ");
            String email = scanner.nextLine();
            if (isValidEmail(email)) {
                for (User u : users) {
                    if (u.getEmail().equals(email)) {
                        System.out.println("email trùng");
                        check = false;
                        break;
                    }
                }
                if (check) {
                    return email;
                }else {
                    System.out.println("email bị trùng");
                }
            } else {
                System.out.println("email sai định dạng");
            }
        }
    }

    public static String phone(Scanner scanner) {
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String phoneNumber = scanner.nextLine();
            if (isValidPhoneNumber(phoneNumber)) {
                return phoneNumber;
            } else {
                System.out.println("phoneNumber sai định dạng");
            }
        }
    }


    public static boolean isValidUsername(String username) {
        String regex = "^(?!.*([!@#$%^&*()\\[\\]{};':\"\\\\|,.<>\\/?])).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(0|\\+84)\\d{9,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
