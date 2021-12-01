package hotel.service;

import hotel.entity.Customer;
import hotel.Dao.CustomerDao;

import java.sql.SQLException;

public class CustomerService {

    public void addCustomer(Customer customer) throws SQLException {
        CustomerDao dao = new CustomerDao();
        dao.addCustomer(customer);
    }

    public Customer getCustomerByName(String name) throws SQLException {
        CustomerDao dao = new CustomerDao();
        return dao.getCustomerByName(name);
    }
}
