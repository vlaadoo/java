package hotel.Dao;

import hotel.entity.Room;
import db.persistance.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    DataBase dbCon = new DataBase();

    public List<Room> getAllExistingRooms() throws SQLException {

        List<Room> rooms = new ArrayList<>();

        dbCon.connect();
        String sql = ("SELECT * FROM rooms");
        ResultSet rs = dbCon.select(sql);

        while (rs.next()) {
            Room room = new Room();

            room.setId(Integer.parseInt(rs.getString("room_id")));
            room.setNumber(rs.getInt("number"));
            room.setPrice(rs.getDouble("price"));
            room.setSize(rs.getInt("size"));
            room.setType(rs.getString("type"));

            rooms.add(room);
        }

        dbCon.disconnect();
        return rooms;
    }

    public List<Room> getAllRoomsByType(String type) throws SQLException {

        List<Room> rooms = new ArrayList<>();
        dbCon.connect();

        String sql = String.format("SELECT * FROM rooms WHERE type = '%s';", type);
        ResultSet rs = dbCon.select(sql);

        while (rs.next()) {
            Room room = new Room();

            room.setId(Integer.parseInt(rs.getString("room_id")));
            room.setNumber(rs.getInt("number"));
            room.setPrice(rs.getDouble("price"));
            room.setSize(rs.getInt("size"));
            room.setType(rs.getString("type"));

            rooms.add(room);
        }
        dbCon.disconnect();

        return rooms;
    }
}
