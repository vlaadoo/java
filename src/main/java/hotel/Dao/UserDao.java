package hotel.Dao;

import hotel.entity.User;
import hotel.entity.UserStatus;
import db.persistance.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    DataBase dbCon = new DataBase();

    public User getUserByEmail(String email) throws SQLException {

        dbCon.connect();

        String sql = String.format("SELECT * FROM users WHERE email = '%s';", email);
        ResultSet rs = dbCon.select(sql);

        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            String status = rs.getString("status");
            switch (status) {
                case "admin": {
                    user.setStatus(UserStatus.ADMIN);
                    break;
                }
                case "user": {
                    user.setStatus(UserStatus.USER);
                    break;
                }
                default: {
                    break;
                }
            }
        }

        dbCon.disconnect();
        return user;
    }

    public List<User> getUserByStatus(UserStatus status) throws SQLException {

        dbCon.connect();

        String lowerCaseStatus = status.toString().toLowerCase();
        String sql = String.format("SELECT * FROM users WHERE status = '%s';", lowerCaseStatus);
        ResultSet rs = dbCon.select(sql);

        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            String userStatus = rs.getString("status");
            switch (userStatus) {
                case "admin": {
                    user.setStatus(UserStatus.ADMIN);
                    break;
                }
                case "user": {
                    user.setStatus(UserStatus.USER);
                    break;
                }
                default: {
                    break;
                }
            }
            users.add(user);
        }

        dbCon.disconnect();
        return users;
    }

    public void addUser(User user) throws SQLException {
        dbCon.connect();

        String sql = "INSERT INTO users (name, email, password, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = dbCon.createPrepState(sql);

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getStatus().toString().toLowerCase());
        stmt.executeUpdate();

        dbCon.disconnect();
    }

    public void changeUserStatus(String userEmail, UserStatus newStatus) throws SQLException {

        dbCon.connect();

        String sql = String.format("UPDATE users SET status = '%s' WHERE email = '%s';",
                newStatus.toString().toLowerCase(), userEmail);

        PreparedStatement stmt = dbCon.createPrepState(sql);

        stmt.executeUpdate();
        dbCon.disconnect();
    }

    public User loginUser(String userEmail, String userPass) throws SQLException {

        dbCon.connect();

        String sql = String.format("SELECT * FROM users WHERE email='%s' AND password='%s';", userEmail, userPass);
        ResultSet rs = dbCon.select(sql);

        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            String status = rs.getString("status");
            switch (status) {
                case "admin": {
                    user.setStatus(UserStatus.ADMIN);
                    break;
                }
                case "user": {
                    user.setStatus(UserStatus.USER);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        dbCon.disconnect();
        return user;
    }

    public UserStatus loginUserStatus(String userEmail, String userPass) throws SQLException {

        dbCon.connect();

        String sql = String.format("SELECT * FROM users WHERE email='%s' AND password='%s';", userEmail, userPass);
        ResultSet rs = dbCon.select(sql);

        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            String status = rs.getString("status");
            switch (status) {
                case "admin": {
                    user.setStatus(UserStatus.ADMIN);
                    break;
                }
                case "user": {
                    user.setStatus(UserStatus.USER);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        dbCon.disconnect();
        return user.getStatus();
    }
}
