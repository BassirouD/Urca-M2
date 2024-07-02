package hosso.diallo.test;

import hosso.diallo.annotations.MyComponent;

import java.util.ArrayList;
import java.util.List;


public class UserRepository {
    List<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public List<User> userList() {
        return users;
    }

}
