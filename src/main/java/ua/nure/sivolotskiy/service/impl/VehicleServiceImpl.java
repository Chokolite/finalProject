package ua.nure.sivolotskiy.service.impl;

import ua.nure.sivolotskiy.entity.Vehicle;
import ua.nure.sivolotskiy.repository.VehicleRepository;
import ua.nure.sivolotskiy.service.VehicleService;
import ua.nure.sivolotskiy.transaction.TransactionManager;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    TransactionManager transactionManager;
    VehicleRepository vehicleRepository;

    public VehicleServiceImpl(TransactionManager transactionManager, VehicleRepository vehicleRepository) {
        this.transactionManager = transactionManager;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle getById(Long id) {
        return transactionManager.execute(c -> vehicleRepository.getById(c, id));
    }

    @Override
    public List<Vehicle> getAll() {
        return transactionManager.execute(c -> vehicleRepository
                .getAll(c));
    }

    @Override
    public Long save(Vehicle vehicle) {
        return transactionManager.execute(c -> {
            return vehicleRepository.save(c, vehicle);
        });
    }

    @Override
    public void delete(Long id) {
        transactionManager.execute(c -> {
            vehicleRepository.delete(c, id);
            return null;
        });
    }

    @Override
    public void update(Vehicle vehicle) {
        transactionManager.execute(c -> {
            vehicleRepository.update(c, vehicle);
            return null;
        });
    }
    @Override
    public void finish(Vehicle vehicle){
        transactionManager.execute(c ->{
            vehicleRepository.finish(c, vehicle);
            return null;
        });
    }
}
