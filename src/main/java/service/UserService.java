package service;

import dao.DAOFactory;
import dao.UserDAO;
import model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static UserService instance;

    private UserDAO dao;

    private UserService(DAOFactory factory) {
        dao = factory.getUserDao();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(
                    DAOFactory.getFactory()
            );
        }

        return instance;
    }

    public void save(User user) {
        dao.save(user);
    }

    public void update(User user) {
        dao.update(user);
    }

    public Optional<User> findById(Long id) {
        return dao.findById(id);
    }

    public Optional<User> findByLogin(String login) {
        return dao.findByLogin(login);
    }

    public List<User> findAll() {
        return dao.findAll();
    }

    public boolean hasById(Long id) {
        return dao.hasById(id);
    }

    public boolean hasByLogin(String login) {
        return dao.hasByLogin(login);
    }

    public boolean deleteById(Long id) {
        return dao.deleteById(id);
    }

    public boolean deleteByLogin(String login) {
        return dao.deleteByLogin(login);
    }

    public void deleteAll() {
        dao.deleteAll();
    }
}