package hotel.service;

import hotel.entity.Room;
import hotel.Dao.RoomDao;

import java.sql.SQLException;
import java.util.List;

public class RoomService {

    public List<Room> getAllExistingRooms() throws SQLException {
        RoomDao dao = new RoomDao();
        return dao.getAllExistingRooms();
    }

    public List<Room> getAllRoomsByType(String type) throws SQLException {
        RoomDao dao = new RoomDao();
        return dao.getAllRoomsByType(type);
    }
}
