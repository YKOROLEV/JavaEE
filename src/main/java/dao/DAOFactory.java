package dao;

import util.PropertiesHelper;

public abstract class DAOFactory {

    public static DAOFactory getFactory() {
        String factory = PropertiesHelper.load("dao.properties")
                .getValue("dao.type");

        switch (factory) {
            case "jdbc": {
                return new JdbcDAOFactory();
            }
            case "hibernate": {
                return new HibernateDAOFactory();
            }
            default: {
                throw new IllegalArgumentException("Factory " + factory + " not found.");
            }
        }
    }

    public abstract UserDAO getUserDao();
}