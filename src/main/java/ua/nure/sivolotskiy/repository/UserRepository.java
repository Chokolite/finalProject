package ua.nure.sivolotskiy.repository;

import ua.nure.sivolotskiy.entity.Dispatcher;
import ua.nure.sivolotskiy.entity.Driver;
import ua.nure.sivolotskiy.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    User getById(Connection connection, Long id) throws SQLException;

    List<User> getAll(Connection connection) throws SQLException;

    User getUserByEmailAndPassword(Connection connection, String email, String password) throws SQLException;

    Long save(Connection connection, User user) throws SQLException;

    void delete(Connection connection, Long id) throws SQLException;

    void update(Connection connection, User user) throws SQLException;

    boolean existsByIdAndEmail(Connection connection, Long id, String email) throws SQLException;

    boolean existsByEmail(Connection connection, String email) throws SQLException;

    List<Driver> getAllDrivers(Connection connection) throws SQLException;

    List<Dispatcher> getAllDispatchers(Connection connection) throws SQLException;

    boolean isEnabledById(Connection c, Long id) throws SQLException;

    void updateEnabled(Connection c, Long id, boolean b) throws SQLException;
}
