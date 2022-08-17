package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" + "id BIGINT PRIMARY KEY AUTO_INCREMENT, " + "name VARCHAR(45), " + "lastName VARCHAR(45), " + "age TINYINT)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при добавлении таблицы users");
        }
        closeConnection();
    }

    public void dropUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при удалении таблицы users");
        }
        closeConnection();
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при добавлении пользователя");
        }
        closeConnection();

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при удалении пользователя");
        }
        closeConnection();

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при получении всех пользователей");
        }
        closeConnection();
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при очистке таблицы users");
        }
        closeConnection();


    }
}
