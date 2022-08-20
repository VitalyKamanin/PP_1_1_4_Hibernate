package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" + "id BIGINT PRIMARY KEY AUTO_INCREMENT, " + "name VARCHAR(45), " + "lastName VARCHAR(45), " + "age TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при добавлении таблицы users");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS users");
            } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при удалении таблицы users");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, lastName);
                ps.setByte(3, age);
                ps.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при добавлении пользователя");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM users")) {
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
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("TRUNCATE TABLE users");
            } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при очистке таблицы users");
        }
    }
}
