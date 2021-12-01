package hotel.Dao;

import hotel.entity.User;
import db.persistance.DataBase;

import java.sql.*;

public class CustomerDao {
    // DataBase dbCon = new DataBase();

    public void addCustomer(User user) throws SQLException {
        String sql = String.format("INSERT INTO customers (email, name, surname) VALUES (%s, %s, %s)", user.getEmail(), 
            user.getName(), user.getSurname(),);
        PreparedStatement stmt = dbCon.createPrepState(sql);

        stmt.setString(1, );
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getSurname());

        stmt.executeUpdate();
        dbCon.disconnect();
    }

    public Customer getCustomerByName(String name) throws SQLException {

        String[] subStr = name.split("\\s+");
        String firstName = subStr[0];
        String lastName = subStr[1];

        dbCon.connect();
        String sql = String.format("SELECT * FROM customers WHERE name = '%s' AND surname = '%s' ;", firstName, lastName);
        ResultSet rs = dbCon.select(sql);

        Customer customer = new Customer();
        while (rs.next()) {

            customer.setId(Integer.parseInt(rs.getString("customer_id")));
            customer.setEmail(rs.getString("email"));
            customer.setName(rs.getString("name"));
            customer.setSurname(rs.getString("surname"));
        }

        dbCon.disconnect();
        return customer;
    }
}
