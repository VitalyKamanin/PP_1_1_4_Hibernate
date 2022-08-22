package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Shinji", "Ikari", (byte) 14);
        userService.saveUser("Rei", "Ayanami", (byte) 14);
        userService.saveUser("Asuka", "Sohryu", (byte) 14);
        userService.saveUser("Misato", "Katsuragi", (byte) 29);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}