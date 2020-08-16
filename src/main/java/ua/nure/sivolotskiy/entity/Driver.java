package ua.nure.sivolotskiy.entity;

public class Driver extends User {
    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
