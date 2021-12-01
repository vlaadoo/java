package hotel.Dao;

public class DaoFactory {
    private static DaoFactory instance;
    private CustomerDao customerDao;
    private ReservationDao reservationDao;
    private RoomDao roomDao;
    private UserDao userDao;

    private DaoFactory() {

    }
}
