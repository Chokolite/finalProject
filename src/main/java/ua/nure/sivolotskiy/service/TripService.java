package ua.nure.sivolotskiy.service;

import ua.nure.sivolotskiy.entity.Trip;

import java.util.List;

public interface TripService {
    Trip getById(Long id);

    List<Trip> getAll(String orderBy, int offset);

    Long save(Trip trip);

    void setBookingService(BookingService bookingService);

    void setVehicleService(VehicleService vehicleService);

    void delete(Long id);

    void update(Trip trip);
}
