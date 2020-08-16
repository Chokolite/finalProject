package ua.nure.sivolotskiy.repository.impl;

import ua.nure.sivolotskiy.entity.Condition;
import ua.nure.sivolotskiy.entity.TrunkSize;
import ua.nure.sivolotskiy.entity.Type;
import ua.nure.sivolotskiy.entity.Vehicle;
import ua.nure.sivolotskiy.repository.VehicleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryImpl implements VehicleRepository {
    private static final String SELECT_ALL_VEHICLE = "select * from vehicle";
    private static final String SELECT_VEHICLE_BY_ID = "SELECT * FROM vehicle where id=?";
    private static final String INSERT_VEHICLE = "INSERT INTO vehicle (name, sits, type, trunkSize, class, `condition`)" +
            "VALUES(?,?,?,?,?,?)";
    private static final String DELETE_VEHICLE = "DELETE FROM vehicle where id=?";
    private static final String UPDATE_VEHICLE = "UPDATE vehicle set name=?, sits=?, type=?, trunkSize=?, class=?," +
            " `condition`=? where id = ?";
    private static final String FINISH_VEHICLE = "UPDATE vehicle set `condition`=? where id=?";


    @Override
    public Vehicle getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_VEHICLE_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToVehicle(resultSet) : null;
    }

    @Override
    public List<Vehicle> getAll(Connection connection) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_VEHICLE);
        while (resultSet.next()) {
            vehicles.add(convertResultSetToVehicle(resultSet));
        }
        return vehicles;
    }

    @Override
    public Long save(Connection connection, Vehicle vehicle) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_VEHICLE, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(vehicle, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE);
        statement.setLong(1, id);
        statement.executeUpdate();
    }


    @Override
    public void update(Connection connection, Vehicle vehicle) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE);
        setAttributeForUpdateVehicle(vehicle, statement);
        statement.executeUpdate();
    }

    @Override
    public void finish(Connection connection, Vehicle vehicle) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FINISH_VEHICLE);
        setAttributeForFinishVehicleAfterTrip(vehicle, statement);
        statement.executeUpdate();

    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    private Vehicle convertResultSetToVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getLong("id"));
        vehicle.setName(resultSet.getString("name"));
        vehicle.setSits(resultSet.getLong("sits"));
        vehicle.setType(Type.valueOf(resultSet.getString("type")));
        vehicle.setTrunkSize(TrunkSize.valueOf(resultSet.getString("trunkSize")));
        vehicle.setCarClass(resultSet.getString("class"));
        vehicle.setCondition(Condition.valueOf(resultSet.getString("condition")));

        return vehicle;
    }

    private void setAttributeForSave(Vehicle vehicle, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setString(count++, vehicle.getName());
        statement.setLong(count++, vehicle.getSits());
        statement.setString(count++, String.valueOf(vehicle.getType()));
        statement.setString(count++, String.valueOf(vehicle.getTrunkSize()));
        statement.setString(count++, String.valueOf(vehicle.getCarClass()));
        statement.setString(count++, String.valueOf(vehicle.getCondition()));
    }
    private void setAttributeForUpdateVehicle(Vehicle vehicle, PreparedStatement statement) throws SQLException {
        int count = 0;
        statement.setString(++count, vehicle.getName());
        statement.setLong(++count, vehicle.getSits());
        statement.setString(++count, String.valueOf(vehicle.getType()));
        statement.setString(++count, String.valueOf(vehicle.getTrunkSize()));
        statement.setString(++count, String.valueOf(vehicle.getCarClass()));
        statement.setString(++count, String.valueOf(vehicle.getCondition()));
        statement.setLong(++count, vehicle.getId());
    }

    private void setAttributeForFinishVehicleAfterTrip(Vehicle vehicle, PreparedStatement statement) throws SQLException {
        int count = 0;
        statement.setString(++count, String.valueOf(vehicle.getCondition()));
        statement.setLong(++count, vehicle.getId());
    }
}
