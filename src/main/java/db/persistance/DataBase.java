//package db.persistance;
//
//import java.sql.*;
//import java.util.Properties;
//
//public class DataBase {
//
//    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&serverTimezone=UTC&useSSL=false";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "root";
//
//    private Connection connection;
//
//    private Properties properties;
//
//    private Statement statement;
//
//    private PreparedStatement preparedStatement;
//
//    private Properties getProperties() {
//        if (properties == null) {
//            properties = new Properties();
//
//            properties.setProperty("user", USERNAME);
//
//            properties.setProperty("password", PASSWORD);
//        }
//
//        return properties;
//    }
//
//
//    //TODO поменять на try with resources
//    // Connect to the database
//    public Connection connect() {
//        if (connection == null) {
//            try {
//                Class.forName(DATABASE_DRIVER);
////                System.out.println("Opened");
//                connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//    // Disconnect database
//    public void disconnect() {
//        if (connection != null) {
//            try {
//                connection.close();
//
//                connection = null;
//                System.out.println("Closed");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}