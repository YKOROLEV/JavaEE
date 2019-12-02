package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void save(User user) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void update(User user) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
    }

    @Override
    public Optional<User> findById(Long id) {
        Session session = getSession();

        Query<User> query = session.createQuery("FROM User WHERE id = :id", User.class);
        query.setParameter("id", id);

        return query.uniqueResultOptional();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Session session = getSession();

        Query<User> query = session.createQuery("FROM User WHERE login = :login", User.class);
        query.setParameter("login", login);

        return query.uniqueResultOptional();
    }

    @Override
    public List<User> findAll() {
        Session session = getSession();
        return session.createQuery("FROM User", User.class)
                .list();
    }

    @Override
    public boolean hasById(Long id) {
        Session session = getSession();

        Query<Boolean> query = session.createQuery("SELECT CASE WHEN count(id) > 0 THEN true ELSE false END FROM User WHERE id = :id", Boolean.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public boolean hasByLogin(String login) {
        Session session = getSession();

        Query<Boolean> query = session.createQuery("SELECT CASE WHEN count(id) > 0 THEN true ELSE false END FROM User WHERE login = :login", Boolean.class);
        query.setParameter("login", login);

        return query.getSingleResult();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean deleteById(Long id) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", id);

        boolean deleted = query.executeUpdate() > 0;
        transaction.commit();

        return deleted;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean deleteByLogin(String login) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE login = :login");
        query.setParameter("login", login);

        boolean deleted = query.executeUpdate() > 0;
        transaction.commit();

        return deleted;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void deleteAll() {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User");
        query.executeUpdate();
        transaction.commit();
    }
}