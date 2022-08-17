package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка соединения с БД");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Соединение с БД закрыто");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка закрытия соединения с БД");
        }
    }
}