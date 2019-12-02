package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlIdentifier")
public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, name, password) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET name = ?, password = ? WHERE id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("name"),
                        rs.getString("password")
                );

                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE login = ?")) {
            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("name"),
                        rs.getString("password")
                );

                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user")) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("name"),
                        rs.getString("password")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean hasById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM user WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean hasByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM user WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE login = ?")) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}