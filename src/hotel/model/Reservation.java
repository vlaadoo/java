package hotel.model;

import java.util.Date;

public class Reservation {

    private final Customer customer;
    private final RoomInfo room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(final Customer customer, final RoomInfo room,
                       final Date checkInDate, final Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public RoomInfo getRoom() {
        return this.room;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    @Override
    public String toString() {
        return "Клиент: " + this.customer.toString() +
                "\nНомер: " + this.room.toString() +
                "\nДата заезда: " + this.checkInDate +
                "\nДата выезда: " + this.checkOutDate;
    }
}
