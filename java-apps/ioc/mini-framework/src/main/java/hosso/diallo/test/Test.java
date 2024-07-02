package hosso.diallo.test;

import hosso.diallo.xml_config.DependencyInjector;

public class Test {
    public static void main(String[] args) {
        DependencyInjector injector = new DependencyInjector();
        injector.injectDependencies("user.xml");
        UserService userService = (UserService) injector.getDependency("userService");
        User user = new User("John", "john@example.com");
        userService.saveUser(user);

    }
}
