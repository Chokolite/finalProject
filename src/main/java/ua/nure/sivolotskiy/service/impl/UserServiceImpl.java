package ua.nure.sivolotskiy.service.impl;

import ua.nure.sivolotskiy.entity.Dispatcher;
import ua.nure.sivolotskiy.entity.Driver;
import ua.nure.sivolotskiy.entity.Entity;
import ua.nure.sivolotskiy.entity.User;
import ua.nure.sivolotskiy.exception.ValidationEnum;
import ua.nure.sivolotskiy.exception.ValidationException;
import ua.nure.sivolotskiy.repository.UserRepository;
import ua.nure.sivolotskiy.service.UserService;
import ua.nure.sivolotskiy.transaction.TransactionManager;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private TransactionManager transactionManager;
    private UserRepository userRepository;

    public UserServiceImpl(TransactionManager transactionManager, UserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        return transactionManager.execute(c -> userRepository.getById(c, id));
    }

    @Override
    public List<User> getAll() {
        return transactionManager.execute(c -> userRepository
                .getAll(c));
    }

    @Override
    public List<Driver> getAllDrivers() {
        return transactionManager.execute(c -> userRepository.getAllDrivers(c));
    }

    @Override
    public List<Dispatcher> getAllDispatchers(){
        return  transactionManager.execute(c -> userRepository.getAllDispatchers(c));
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return transactionManager.execute(c -> userRepository.getUserByEmailAndPassword(c, email, password));
    }

    @Override
    public Long save(User user) {
        return transactionManager.execute(c -> userRepository.save(c, user));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User user) {
        if (existsByIdAndEmail(user.getId(), user.getEmail())) {
            ValidationException.builder().put("emailError", ValidationEnum.EMAIL_EXISTS);
        } else {
            transactionManager.execute(c -> {
                userRepository.update(c, user);
                return null;
            });
        }
    }

    private boolean existsByIdAndEmail(Long id, String email) {
        return transactionManager.execute(c -> userRepository.existsByIdAndEmail(c, id, email));
    }


    @Override
    public boolean existsByEmail(String email) {
        return transactionManager.execute(c -> userRepository.existsByEmail(c, email));
    }

    @Override
    public boolean isEnabledById(Long id) {
        return transactionManager.execute(c -> userRepository.isEnabledById(c, id));
    }

    @Override
    public void updateEnabled(Long id, boolean b) {
        transactionManager.execute(c -> {
            userRepository.updateEnabled(c, id, b);
            return null;
        });
    }
}

