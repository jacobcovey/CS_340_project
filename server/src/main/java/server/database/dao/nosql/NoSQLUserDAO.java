package server.database.dao.nosql;

import server.database.dao.iUserDAO;
import shared.classes.User;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLUserDAO implements iUserDAO {
    @Override
    public boolean create() {
        return true;
    }

    @Override
    public User read() {
        return null;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public boolean delete() {
        return true;
    }
}
