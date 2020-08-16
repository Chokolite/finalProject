package ua.nure.sivolotskiy.repository.impl;

import ua.nure.sivolotskiy.entity.Dispatcher;
import ua.nure.sivolotskiy.entity.Driver;
import ua.nure.sivolotskiy.entity.Role;
import ua.nure.sivolotskiy.entity.User;
import ua.nure.sivolotskiy.repository.UserRepository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_ALL = "select * from users";
    private static final String SELECT_ALL_DRIVERS = "select dr.enabled dr_enabled, dr.id dr_id, dr.name dr_name," +
            " dr.email dr_email where dr.role='DRIVER'";
    private static final String SELECT_ALL_DISPATCHERS = "select di.enabled di_enabled, di.id u_id, di.name di_name," +
            " di.email u_email where di.role='DISPATCHER'";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "select * from users where email = ? and password = ?";
    private static final String INSERT_USER = "insert into users (name, password, email, role) values(?,?,?,?)";
    private static final String UPDATE_USER = "update users set name=?, password=?, email=? where id=?";
    private static final String UPDATE_ENABLED = "update users set enabled=? where id=?";
    private static final String DELETE_USER = "delete from uesers where id=?";
    private static final String EXIST_USER_BY_EMAIL = "select 1 from users where email=?";
    private static final String EXIST_USER_BY_ID_AND_EMAIL = "select 1 from users where id=? and email=?";
    private static final String EXISTS_USER_BY_ID_AND_ENABLED = "select 1 from users where id = ? and enabled = 1";

    @Override
    public User getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToUser(resultSet) : null;
    }

    @Override
    public List<User> getAll(Connection connection) throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        while (resultSet.next()) {
            list.add(convertResultSetToUser(resultSet));
        }
        return list;
    }

    @Override
    public User getUserByEmailAndPassword(Connection connection, String email, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToUser(resultSet) : null;
    }

    @Override
    public Long save(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(user, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_USER);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public void update(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
        setAttributeForUpdate(user, statement);
        statement.executeUpdate();
    }

    @Override
    public boolean existsByIdAndEmail(Connection connection, Long id, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(EXIST_USER_BY_ID_AND_EMAIL);
        statement.setLong(1, id);
        statement.setString(2, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public boolean existsByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(EXIST_USER_BY_EMAIL);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public List<Driver> getAllDrivers(Connection connection) throws SQLException {
        List<Driver> userList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_DRIVERS);
        while (resultSet.next()){
            userList.add(convertResultSetToDriver(resultSet));
        }
        return userList;
    }

    @Override
    public List<Dispatcher> getAllDispatchers(Connection connection) throws SQLException{
        List<Dispatcher> dispatcherList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_DISPATCHERS);
        while (resultSet.next()){
            dispatcherList.add(convertResultSetToDispatcher(resultSet));
        }
        return dispatcherList;
    }

    @Override
    public boolean isEnabledById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(EXISTS_USER_BY_ID_AND_ENABLED);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void updateEnabled(Connection connection, Long id, boolean b) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_ENABLED);
        statement.setBoolean(1, b);
        statement.setLong(2, id);
        statement.executeUpdate();
    }

    private User convertResultSetToUser(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setName(resultSet.getString("name"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));

        return user;
    }
    private Driver convertResultSetToDriver(ResultSet resultSet) throws SQLException {
        Driver user = new Driver();
        user.setId(resultSet.getLong("dr_id"));
        user.setRole(Role.valueOf(resultSet.getString("dr_role")));
        user.setName(resultSet.getString("dr_name"));
        user.setEnabled(resultSet.getBoolean("dr_enabled"));
        user.setPassword(resultSet.getString("dr_password"));
        user.setEmail(resultSet.getString("dr_email"));
        return user;
    }

    private Dispatcher convertResultSetToDispatcher(ResultSet resultSet) throws SQLException {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setId(resultSet.getLong("di_id"));
        dispatcher.setRole(Role.valueOf(resultSet.getString("di_role")));
        dispatcher.setName(resultSet.getString("di_name"));
        dispatcher.setEnabled(resultSet.getBoolean("di_enabled"));
        dispatcher.setPassword(resultSet.getString("di_password"));
        dispatcher.setEmail(resultSet.getString("di_email"));
        return dispatcher;
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    private void setAttributeForSave(User user, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, user.getName());
        statement.setString(count++, user.getPassword());
        statement.setString(count++, user.getEmail());
        statement.setString(count++, user.getRole().name());
    }

    private void setAttributeForUpdate(User user, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, user.getName());
        statement.setString(count++, user.getPassword());
        statement.setString(count, user.getEmail());
    }
}
