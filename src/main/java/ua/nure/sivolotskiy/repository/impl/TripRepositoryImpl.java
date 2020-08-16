package ua.nure.sivolotskiy.repository.impl;

import ua.nure.sivolotskiy.entity.*;
import ua.nure.sivolotskiy.repository.TripRepository;
import ua.nure.sivolotskiy.repository.sql.SelectBuilder;
import ua.nure.sivolotskiy.repository.sql.SqlBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripRepositoryImpl implements TripRepository {

    private static final String DEFAULT_ORDER = "t.id,ASC";
    private static final String SELECT_ALL_TRIPS = "select t.id t_id, t.vehicleId t_vid, t.bookingId t_bid," +
            " t.createDate t_cdate, t.status t_status, t.task t_task" +
            "  from trip t left join vehicle v ON t.vehicleId=v.id left join booking b on t.bookingId=b.id";
    private static final String SELECT_JOIN_BOOKING_AND_VEHICLE = "SELECT t.id t_id, t.vehicleId t_vehicleId," +
            " t.bookingId t_bookingId, t.createDate t_cdate, t.status t_status" +
            "FROM trip t LEFT JOIN booking b ON t.bookingId=b.id" +
            "LEFT JOIN vehicle v ON t.vehicleId=v.id";
    private static final String SELECT_TRIP_BY_ID = SELECT_ALL_TRIPS + " where t.id=?";
    private static final String INSERT_TRIP = "INSERT INTO trip(createDate, status, task)" +
            " VALUES(?,?,?)";
    private static final String UPDATE_TRIP = "UPDATE trip SET id=?, vehicleId=?, bookingId=?, createDate=?, status=?" +
            " WHERE id=?";
    private static final String DElETE_TRIP = "DELETE FROM trip WHERE id=?";

    @Override
    public Trip getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_TRIP_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToTrips(resultSet) : null;
    }

    //    @Override
//    public List<Trip> getAll(Connection connection, Long id, Long vehicleId,
//                             Long bookingId, Date date, Enum<Status> statusEnum) throws SQLException {
//        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TRIPS);
//        ResultSet resultSet = statement.executeQuery();
//
//        List<Trip> trips = new ArrayList<>();
//        while (resultSet.next()) {
//            trips.add(convertResultSetToTrips(resultSet));
//        }
//        return trips;
//    }
    @Override
    public List<Trip> getAll(Connection connection, String orderBy, int offset) throws SQLException {
        SelectBuilder select = SqlBuilder.select(SELECT_ALL_TRIPS);
        setOrder(orderBy, select);

        PreparedStatement statement = select.buildPrepareStatement(connection);
        ResultSet resultSet = statement.executeQuery();

        List<Trip> trips = new ArrayList<>();
        while (resultSet.next()) {
            trips.add(convertResultSetToTrips(resultSet));
        }
        return trips;
    }

    @Override
    public Long save(Connection connection, Trip trip) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_TRIP, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSaveTrip(trip, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DElETE_TRIP);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public void update(Connection connection, Trip trip) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_TRIP);
        setAttributeForUpdateTrip(trip, statement);
        statement.executeUpdate();
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    private Trip convertResultSetToTrips(ResultSet resultSet) throws SQLException {

        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getLong("t_vid"));
        Booking booking = new Booking();
        booking.setId(resultSet.getLong("t_bid"));
        Trip trip = new Trip();
        trip.setId(resultSet.getLong("t_id"));
        trip.setVehicle(vehicle);
        trip.setBooking(booking);
        trip.setCreateDate(resultSet.getDate("t_cdate"));
        trip.setStatus(Status.valueOf(resultSet.getString("t_status")));
        trip.setTask(resultSet.getString("t_task"));

        return trip;
    }

    private void setAttributeForSaveTrip(Trip trip, PreparedStatement statement) throws SQLException {
        int count = 0;
   //     statement.setLong(++count, trip.getVehicle().getId()); //changed this. Trip creating won't work with uncommented
     //   statement.setLong(++count, trip.getBooking().getId()); //and this
        statement.setDate(++count, trip.getCreateDate());
        statement.setString(++count, String.valueOf(trip.getStatus()));
        statement.setString(++count, trip.getTask());
    }

    private void setAttributeForUpdateTrip(Trip trip, PreparedStatement statement) throws SQLException {
        int count = 0;
        statement.setLong(++count, trip.getId());
        statement.setLong(++count, trip.getVehicle().getId());
        statement.setLong(++count, trip.getBooking().getId());
        statement.setDate(++count, trip.getCreateDate());
        statement.setString(++count, String.valueOf(trip.getStatus()));
        statement.setLong(++count, trip.getId());
    }

    private void setOrder(String orderBy, SelectBuilder select) {
        if (Objects.isNull(orderBy)) {
            orderBy = DEFAULT_ORDER;
        }
        String field = orderBy.split(",")[0];
        boolean ascending = !orderBy.split(",")[1].equals("DESC");
        select.order(field, ascending);
    }

    private void setFilter(String tripStatus, SelectBuilder select) {
        if (Objects.nonNull(tripStatus)) {
            select.where().like("t.status", tripStatus);
        }
    }
}
