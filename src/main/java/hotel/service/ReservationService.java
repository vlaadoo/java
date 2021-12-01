package hotel.service;

import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.Dao.ReservationDao;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    public List<Room> roomList;

    public List<Reservation> getAllReservations() throws SQLException{
        ReservationDao dao = new ReservationDao();
        return dao.getAllReservations();
    }

    public List<Reservation> getAllReservationsByRoomNumber(int number) throws SQLException {
        ReservationDao dao = new ReservationDao();
        return dao.getAllReservationsByRoomNumber(number);
    }

    public void addReservation(Reservation newBoooking) throws SQLException {
        ReservationDao dao = new ReservationDao();
        dao.addReservation(newBoooking);
    }

    public void deleteReservation(int ReservationNumber) throws SQLException {;
        ReservationDao dao = new ReservationDao();
        dao.deleteReservation(ReservationNumber);
    }

    public List<Reservation> getAllReservationsByCustomerName(int cust_id) throws SQLException {
        ReservationDao dao = new ReservationDao();
        return dao.getAllReservationsByCustomerId(cust_id);
    }
}