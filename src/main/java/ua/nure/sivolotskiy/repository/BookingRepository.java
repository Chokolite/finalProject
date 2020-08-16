package ua.nure.sivolotskiy.repository;

import ua.nure.sivolotskiy.entity.Booking;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookingRepository {

    Booking getById(Connection connection, Long id) throws SQLException;

//    List<Booking> getAll(Connection connection, Long id, Long user_id, String vehicle_specification) throws SQLException;
List<Booking> getAll(Connection connection) throws SQLException;

    Long save(Connection connection, Booking booking) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;

    void deleteByUserId(Connection connection, Long id) throws SQLException;

    void update(Connection connection, Booking booking) throws SQLException;
}
