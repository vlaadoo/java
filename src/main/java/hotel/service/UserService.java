package hotel.service;

import hotel.entity.User;
import hotel.entity.UserStatus;
import hotel.Dao.UserDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private Map<Integer, UserService> actionMap= new HashMap<>();


    public User getUserByEmail(String email) throws SQLException {
        UserDao dao = new UserDao();
        return dao.getUserByEmail(email);
    }

    public List<User> getUserByStatus(UserStatus status) throws SQLException{
        UserDao dao = new UserDao();
        return dao.getUserByStatus(status);
    }

    public void addUser(User user) throws SQLException {
        UserDao dao = new UserDao();
        dao.addUser(user);
    }

    public void changeUserStatus(String userEmail, UserStatus newStatus) throws SQLException {
        UserDao dao = new UserDao();
        dao.changeUserStatus(userEmail, newStatus);
    }

    public User loginUser(String userEmail, String userPass) throws SQLException {
        UserDao dao = new UserDao();
        return dao.loginUser(userEmail, userPass);
    }
    public UserStatus loginUserStatus(String userEmail, String userPass) throws SQLException {
        UserDao dao = new UserDao();
        return dao.loginUserStatus(userEmail, userPass);
    }
}
