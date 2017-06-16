package server.database.dao;

import java.util.List;
import java.util.Set;

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
    public Set<User> read() {
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
