package ua.nure.sivolotskiy.repository;

import ua.nure.sivolotskiy.entity.Vehicle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VehicleRepository {
    Vehicle getById(Connection connection, Long id) throws SQLException;

    List<Vehicle> getAll(Connection connection) throws SQLException;

    Long save(Connection connection, Vehicle vehicle) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;

    void update(Connection connection, Vehicle vehicle) throws SQLException;

    void finish(Connection connection, Vehicle vehicle) throws SQLException;
}
