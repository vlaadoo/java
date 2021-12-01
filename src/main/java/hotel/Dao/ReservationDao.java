package hotel.Dao;

import hotel.entity.Reservation;
import db.persistance.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
    DataBase dbCon = new DataBase();

    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        dbCon.connect();
        String sql = ("SELECT * FROM reservation");
        ResultSet rs = dbCon.select(sql);


        while (rs.next()) {
            Reservation reservation = new Reservation();

            reservation.setId(Integer.parseInt(rs.getString("reservation_id")));
            reservation.setCustomerId(Integer.parseInt(rs.getString("cust_id")));
            reservation.setRoomNumber(Integer.parseInt((rs.getString("room_number"))));
            reservation.setCheckInDate(rs.getDate("check_in_date"));
            reservation.setCheckOutDate(rs.getDate("check_out_date"));

            reservations.add(reservation);
        }
        dbCon.disconnect();
        return reservations;
    }

    public List<Reservation> getAllReservationsByRoomNumber(int number) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        dbCon.connect();
        String sql = String.format("SELECT * FROM reservations WHERE room_number = %d;", number);
        ResultSet rs = dbCon.select(sql);

        while (rs.next()) {
            Reservation reservation = new Reservation();

            reservation.setId(Integer.parseInt(rs.getString("reservation_id")));
            reservation.setRoomNumber(Integer.parseInt(rs.getString("cust_id")));
            reservation.setRoomNumber(Integer.parseInt((rs.getString("room_number"))));
            reservation.setCheckInDate(rs.getDate("check_in_date"));
            reservation.setCheckOutDate(rs.getDate("check_out_date"));

            reservations.add(reservation);
        }
        dbCon.disconnect();
        return reservations;
    }

    public void addReservation(Reservation reservation) throws SQLException {
        dbCon.connect();
        String sql = "INSERT INTO reservations (cust_id, room_number, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = dbCon.createPrepState(sql);
        stmt.setInt(1, reservation.getCustomerId());
        stmt.setInt(2, reservation.getRoomNumber());
        stmt.setDate(3, (Date) reservation.getCheckInDate());
        stmt.setDate(4, (Date) reservation.getCheckOutDate());
        stmt.executeUpdate();

        dbCon.disconnect();
    }

    public void deleteReservation(int reservation_id) throws SQLException {
        dbCon.connect();

        String delete = String.format("DELETE FROM reserved WHERE reservation_id = '%d';", reservation_id);
        PreparedStatement stmt = dbCon.createPrepState(delete);
        stmt.executeUpdate();

        dbCon.disconnect();
    }

    public List<Reservation> getAllReservationsByCustomerId(int cust_id) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        dbCon.connect();

        String sql = String.format("SELECT * FROM reservations WHERE cust_id = '%d';", cust_id);
        ResultSet rs = dbCon.select(sql);

        while (rs.next()) {
            Reservation reservation = new Reservation();

            reservation.setId(Integer.parseInt(rs.getString("reservation_id")));
            reservation.setCustomerId(Integer.parseInt(rs.getString("cust_id")));
            reservation.setRoomNumber(Integer.parseInt(rs.getString("room_number")));
            reservation.setCheckInDate(rs.getDate("check_in_date"));
            reservation.setCheckOutDate(rs.getDate("check_out_date"));

            reservations.add(reservation);
        }

        dbCon.disconnect();
        return reservations;
    }
}
