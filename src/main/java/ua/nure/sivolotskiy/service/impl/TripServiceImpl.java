package ua.nure.sivolotskiy.service.impl;

import ua.nure.sivolotskiy.entity.Status;
import ua.nure.sivolotskiy.entity.Trip;
import ua.nure.sivolotskiy.entity.Vehicle;
import ua.nure.sivolotskiy.repository.BookingRepository;
import ua.nure.sivolotskiy.repository.TripRepository;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.service.TripService;
import ua.nure.sivolotskiy.service.VehicleService;
import ua.nure.sivolotskiy.transaction.TransactionManager;

import java.sql.Date;
import java.util.List;

public class TripServiceImpl implements TripService {

    private TransactionManager transactionManager;
    private TripRepository tripRepository;


    public TripServiceImpl(TransactionManager transactionManager, TripRepository tripRepository) {
        this.transactionManager = transactionManager;
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip getById(Long id) {
        return transactionManager.execute(c -> tripRepository.getById(c, id));
    }

    @Override
    public List<Trip> getAll( String orderBy, int offset) {
        return transactionManager.execute(c -> tripRepository
                .getAll(c, orderBy, offset));
    }

    @Override
    public Long save(Trip trip) {
        return transactionManager.execute(c -> {
            tripRepository.save(c, trip);
            return null;
        });
    }


    @Override
    public void delete(Long id) {
        transactionManager.execute(c -> {
            tripRepository.delete(c, id);
            return null;
        });
    }

    @Override
    public void update(Trip trip) {
        transactionManager.execute(c -> {
            tripRepository.update(c, trip);
            return null;
        });
    }
}
