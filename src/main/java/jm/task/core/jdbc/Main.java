package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Shinji", "Ikari", (byte) 14);
        userDao.saveUser("Rei", "Ayanami", (byte) 14);
        userDao.saveUser("Asuka", "Sohryu", (byte) 14);
        userDao.saveUser("Misato", "Katsuragi", (byte) 29);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }

}