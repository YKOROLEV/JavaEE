package dao;

import util.DBHelper;

public class JdbcDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDao() {
        return new UserJdbcDAO(DBHelper.getInstance().getConnection());
    }
}