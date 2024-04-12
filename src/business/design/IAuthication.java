package business.design;

import business.entity.User;

public interface IAuthication {
    User login(String username,String password);
    void register(User user);
}
