package ua.nure.sivolotskiy.service;

import ua.nure.sivolotskiy.entity.Dispatcher;
import ua.nure.sivolotskiy.entity.Driver;
import ua.nure.sivolotskiy.entity.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    List<User> getAll();

    List<Driver> getAllDrivers();

    List<Dispatcher> getAllDispatchers();

    Long save(User user);

    void delete(Long id);

    void update(User user);

    User getUserByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean isEnabledById(Long id);

    void updateEnabled(Long id, boolean b);
}
