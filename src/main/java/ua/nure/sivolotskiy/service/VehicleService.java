package ua.nure.sivolotskiy.service;

import ua.nure.sivolotskiy.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle getById(Long id);

    List<Vehicle> getAll();

    Long save(Vehicle vehicle);

    void delete(Long id);

    void update(Vehicle vehicle);

    void finish(Vehicle vehicle);
}
