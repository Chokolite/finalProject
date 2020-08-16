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

    private static final String SELECT_ALL_BOOKING = "select b.id b_id, b.vehicleSpecification b_vehicleSpecification," +
            " u.id u_id, t.id t_id from booking b LEFT JOIN users u ON u.id=b.userId " +
            "LEFT JOIN trip t ON t.id=b.tripId";

    private static final String SELECT_BOOKING_BY_ID = SELECT_ALL_BOOKING + " where b.id = ?";

    private static final String INSERT_BOOKING = "insert into booking (userId, tripId, vehicleSpecification)" +
            "VALUES(?,?,?)";

    private static final String UPDATE_BOOKING = "UPDATE booking SET tripId=?, userId=?, vehicleSpecification=?" +
            "where id=?";

    private static final String DELETE_BOOKING = "DELETE FROM booking WHERE id=?";

    @Override
    public Booking getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BOOKING_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToBooking(resultSet) : null;
    }

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
    public void update(Connection connection, Booking booking) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKING);
        setAttributeForUpdateBooking(booking, statement);
        statement.executeUpdate();
    }

    private Booking convertResultSetToBooking(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setId(resultSet.getLong("b_id"));
        booking.setVehicleSpecification(resultSet.getString("b_vehicleSpecification"));
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
        statement.setLong(++count, booking.getUser().getId());
        statement.setLong(++count, booking.getTrip().getId());
        statement.setString(++count, booking.getVehicleSpecification());
    }

    private void setAttributeForUpdateBooking(Booking booking, PreparedStatement statement) throws SQLException {
        int count = 0;

        statement.setLong(++count, booking.getUser().getId());
        statement.setLong(++count, booking.getTrip().getId());
        statement.setString(++count, booking.getVehicleSpecification());
        statement.setLong(++count, booking.getId());
    }
}
