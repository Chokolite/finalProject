package ua.nure.sivolotskiy.repository.impl;

import ua.nure.sivolotskiy.entity.Booking;
import ua.nure.sivolotskiy.entity.Entity;
import ua.nure.sivolotskiy.entity.Trip;
import ua.nure.sivolotskiy.repository.BookingRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    private static final String SELECT_ALL_BOOKING = "select b.id b_id, b.vehicle_specification b_vehicle_specification," +
            " u.id u_id, t.id t_id from booking b LEFT JOIN users u ON u.id=b.user_id " +
            "LEFT JOIN trip t ON t.id=b.trip_id";

//    private static final String SELECT_JOIN_USER_ID = "select b.id b_id, b.vehicle_specification b_vehicle_specification, " +
//            "u.id u_id, u.name u_name " +
//            "from booking b LEFT JOIN users u ON u.id=b.user_id";
    private static final String SELECT_JOIN_USER_ID = "select b.id b_id, b.vehicle_specification b_vehicle_specification, u.id u_id, u.name u_name from booking b LEFT JOIN users u ON u.id=b.user_id;";

    private static final String SELECT_BOOKING_BY_ID = SELECT_ALL_BOOKING + " where b.id = ?";

    private static final String SELECT_BOOKING_BY_USER_ID = "SELECT b.id b_id, b.vehicle_specification b_vehicle_specification, " +
            "u.id u_id, u.name u_name" +
            "from booking b" +
            "join users u on u.id=b.user_id" +
            "where user_id=?";

    private static final String INSERT_BOOKING = "insert into booking (user_id, trip_id, vehicle_specification)" +
            "VALUES(?,?,?)";

    private static final String UPDATE_BOOKING = "UPDATE booking SET trip_id=?, user_id=?, vehicle_specification=?" +
            "where id=?";

    private static final String DELETE_BOOKING = "DELETE FROM booking WHERE id=?";

    private static final String DELETE_BOOKING_BY_USER_ID = "DELETE FROM booking where user_id=?";

    @Override
    public Booking getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BOOKING_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToBooking(resultSet) : null;
    }

    //    @Override
//    public List<Booking> getAll(Connection connection, Long id, Long user_id, String vehicle_specification) throws SQLException {
//        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKING);
//        ResultSet resultSet = statement.executeQuery();
//
//        List<Booking> bookings = new ArrayList<>();
//        while (resultSet.next()) {
//            bookings.add(convertResultSetToBooking(resultSet));
//        }
//        return bookings;
//    }
    @Override
    public List<Booking> getAll(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKING);
        ResultSet resultSet = statement.executeQuery();

        List<Booking> bookings = new ArrayList<>();
        while (resultSet.next()) {
            bookings.add(convertResultSetToBooking(resultSet));
        }
        return bookings;
    }

    @Override
    public Long save(Connection connection, Booking booking) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_BOOKING, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(booking, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_BOOKING);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public void deleteByUserId(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_BOOKING_BY_USER_ID);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public void update(Connection connection, Booking booking) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKING);
        setAttributeForUpdateBooking(booking, statement);
        statement.executeUpdate();
    }

    private Booking convertResultSetToBooking(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setId(resultSet.getLong("b_id"));
        booking.setVehicle_specification(resultSet.getString("b_vehicle_specification"));
        Entity user = new Entity();
        user.setId(resultSet.getLong("u_id"));
        Trip trip = new Trip();
        trip.setId(resultSet.getLong("t_id"));
        booking.setUser(user);
        booking.setTrip(trip);

        return booking;
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    private void setAttributeForSave(Booking booking, PreparedStatement statement) throws SQLException {
        int count = 0;
//        statement.setLong(++count, booking.getId());
        statement.setLong(++count, booking.getUser().getId());
        statement.setLong(++count, booking.getTrip().getId());
        statement.setString(++count, booking.getVehicle_specification());
    }

    private void setAttributeForUpdateBooking(Booking booking, PreparedStatement statement) throws SQLException {
        int count = 0;

        statement.setLong(++count, booking.getUser().getId());
        statement.setLong(++count, booking.getTrip().getId());
        statement.setString(++count, booking.getVehicle_specification());
        statement.setLong(++count, booking.getId());
    }
}
