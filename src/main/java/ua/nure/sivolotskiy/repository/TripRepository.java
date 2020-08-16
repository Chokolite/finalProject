package ua.nure.sivolotskiy.repository;

import ua.nure.sivolotskiy.entity.Trip;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TripRepository {
    Trip getById(Connection connection, Long id) throws SQLException;

    List<Trip> getAll(Connection connection, String orderBy, int offset) throws SQLException;

    Long save(Connection connection, Trip trip) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;

    void update(Connection connection, Trip trip) throws SQLException;
}
