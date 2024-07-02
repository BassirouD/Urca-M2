package hosso.diallo.test;

import hosso.diallo.annotations.MyAutowired;
import hosso.diallo.annotations.MyComponent;

import java.util.List;

public class UserService {

    @MyAutowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> users() {
        return userRepository.userList();
    }
}
