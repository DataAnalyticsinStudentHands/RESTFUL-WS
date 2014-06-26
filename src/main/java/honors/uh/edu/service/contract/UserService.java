package honors.uh.edu.service.contract;

import honors.uh.edu.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getById(int id);
    
    List<User> getByName(String name);

    User createNewUser(User user);

    User update(User user);

    void remove(int id);

    int getNumberOfUsers();
}
