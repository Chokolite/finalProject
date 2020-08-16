package ua.nure.sivolotskiy.repository;

import ua.nure.sivolotskiy.entity.Status;
import ua.nure.sivolotskiy.entity.Trip;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface TripRepository {
    Trip getById(Connection connection, Long id) throws SQLException;

//    List<Trip> getAll(Connection connection, Long id,
//                      Long vehicle_id, Long booking_id, Date date, Enum<Status> statusEnum) throws SQLException;
    List<Trip> getAll(Connection connection, String orderBy, int offset) throws SQLException;

    Long save(Connection connection, Trip trip) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;

    void update(Connection connection, Trip trip) throws SQLException;

}
