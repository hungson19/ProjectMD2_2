package business.designimpl;

import business.design.IUser;
import business.entity.User;

import business.entity.enumModel.Role;
import org.mindrot.jbcrypt.BCrypt;
import utils.IOFile;
import utils.validate.UserValid;

import java.util.*;

public class UserService implements IUser {
    private static Map<String, User> users = new HashMap<>();
    private User CurrentUser;

    public User getCurrentUser() {
        return CurrentUser;
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static void setUsers(Map<String, User> users) {
        UserService.users = users;
    }

    public void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public UserService() {
        users = IOFile.readFromFile(IOFile.USER_PATH);
        if (users.isEmpty()){
            User user = new User(1, "admin", "1", "1", BCrypt.hashpw("admin", BCrypt.gensalt()), "1", "1", "1");
            user.setRole(Role.ROLE_ADMIN);
            users.put("admin", user);
            IOFile.writeToFile(IOFile.USER_PATH, users);
        }
    }

    public void register(Scanner scanner) {
        String username = UserValid.username(scanner,users);
        String email = UserValid.email(scanner, (Collection<User>) users.values());
        String phoneNumber = UserValid.phone(scanner);
        String password = UserValid.password(scanner);
        String firstName = UserValid.firstName(scanner);
        String lastName = UserValid.lastName(scanner);
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        List<User> users1 = new ArrayList<>(users.values());
        int id;
        if (users1.size() != 0) {
            id = users1.get(users1.size() - 1).getUserId() + 1;
        } else {
            id = 1;
        }
        // Tạo đối tượng User với thông tin đã nhập
        User newUser = new User(id, username, firstName, lastName, hashedPassword, email, phoneNumber, address);

        users.put(username, newUser);
        IOFile.writeToFile(IOFile.USER_PATH, users);
        System.out.println("Đăng ký thành công.");
    }

    public User login(Scanner scanner) {
        System.out.print("Nhập tên người dùng: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        if (users.containsKey(username)) {
            User user = users.get(username);
            // Kiểm tra mật khẩu đã mã hóa với mật khẩu nhập vào
            if (BCrypt.checkpw(password, user.getPassword()) && user.isStatus()) {
                return user;
            }
        }
        return null;
    }

    public void displayUsers() {
        System.out.println("Danh sách người dùng:");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void searchUserByName(String name) {
        List<User> foundUsers = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getUsername().contains(name)) {
                foundUsers.add(user);
            }
        }
        if (foundUsers.isEmpty()) {
            System.out.println("Không tìm thấy người dùng nào có tên chứa '" + name + "'.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (User user : foundUsers) {
                System.out.println(user);
            }
        }
    }

    public void blockUser(int id) {
        for (User user : users.values()) {
            if (user.getUserId() == id) {
                user.setStatus(false);
                IOFile.writeToFile(IOFile.USER_PATH, users);
                System.out.println("Tài khoản " + user.getUsername() + " đã bị khóa.");
            }
        }
    }

    public void unblockUser(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.setStatus(true);
            IOFile.writeToFile(IOFile.USER_PATH, users);
            System.out.println("Tài khoản " + username + " đã được mở khóa.");
        } else {
            System.out.println("Không tìm thấy người dùng có tên là " + username + ".");
        }
    }

    public void changePassword(Scanner scanner, User currentUser) {
        System.out.print("Nhập mật khẩu hiện tại: ");
        String currentPassword = scanner.nextLine();
        if (BCrypt.checkpw(currentPassword, currentUser.getPassword())) {
            String newPassword = UserValid.password(scanner);
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            currentUser.setPassword(hashedPassword);
            IOFile.writeToFile(IOFile.USER_PATH, users);
            System.out.println("Mật khẩu đã được thay đổi.");
        } else {
            System.out.println("Mật khẩu hiện tại không đúng.");
        }
    }


    public void displayUserProfile(User user) {
        System.out.println("Thông tin cá nhân:");
        System.out.println("Họ và tên: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Số điện thoại: " + user.getPhoneNumber());
        System.out.println("Địa chỉ: " + user.getAddress());
        System.out.println("Quyền: " + user.getRole());
    }

    public void editUserProfile(Scanner scanner, User currentUser) {
        System.out.println("Chỉnh sửa thông tin cá nhân:");
        System.out.println("1. Họ và tên");
        System.out.println("2. Email");
        System.out.println("3. Số điện thoại");
        System.out.println("4. Địa chỉ");
        System.out.println("0. Thoát");
        System.out.print("Chọn mục cần chỉnh sửa: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                String newFirstName = UserValid.firstName(scanner);
                currentUser.setFirstName(newFirstName);
                String newLastName = UserValid.lastName(scanner);
                currentUser.setLastName(newLastName);
                break;
            case 2:
                String newEmail = UserValid.email(scanner, users.values());
                currentUser.setEmail(newEmail);
                break;
            case 3:
                String newPhoneNumber = UserValid.phone(scanner);
                currentUser.setPhoneNumber(newPhoneNumber);
                break;
            case 4:
                System.out.print("Nhập địa chỉ: ");
                String newAddress = scanner.nextLine();
                currentUser.setAddress(newAddress);
                break;
            case 0:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }

        IOFile.writeToFile(IOFile.USER_PATH, users);
        System.out.println("Thông tin cá nhân đã được cập nhật.");
    }

}
