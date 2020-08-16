package ua.nure.sivolotskiy.service;

import ua.nure.sivolotskiy.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking getById(Long id);

    //    List<Booking> getAll(Long id, Long usear_id, String vehicle_specification);
    List<Booking> getAll();

    Long save(Booking booking);

    void delete(Long id);

    void deleteByUserId(Long id);

    void update(Booking booking);

}
