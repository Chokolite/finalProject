package ua.nure.sivolotskiy.service.impl;

import ua.nure.sivolotskiy.entity.Booking;
import ua.nure.sivolotskiy.repository.BookingRepository;
import ua.nure.sivolotskiy.service.BookingService;
import ua.nure.sivolotskiy.transaction.TransactionManager;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private TransactionManager transactionManager;
    private BookingRepository bookingRepository;

    public BookingServiceImpl(TransactionManager transactionManager, BookingRepository bookingRepository) {
        this.transactionManager = transactionManager;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking getById(Long id) {
        return transactionManager.execute(c -> bookingRepository.getById(c, id));
    }

    @Override
    public List<Booking> getAll() {
        return transactionManager.execute(c -> bookingRepository.getAll(c));
    }

    @Override
    public Long save(Booking booking) {

        return transactionManager.execute(c -> bookingRepository.save(c, booking));
    }

    @Override
    public void delete(Long id) {
        transactionManager.execute(c -> {
            bookingRepository.delete(c, id);
            return null;
        });
    }

    @Override
    public void update(Booking booking) {
        transactionManager.execute(c -> {
            bookingRepository.update(c, booking);
            return null;
        });
    }
}
