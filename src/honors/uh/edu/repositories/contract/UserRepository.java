package honors.uh.edu.repositories.contract;

import honors.uh.edu.pojo.User;

public interface UserRepository extends Repository<User> {
    User create(User user);

    User update(User user);

    void remove(int id);

    int getNumberOfUsers();
}
