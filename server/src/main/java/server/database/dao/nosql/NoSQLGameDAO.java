package server.database.dao.nosql;

import server.database.dao.iGameDAO;
import shared.classes.Game;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLGameDAO implements iGameDAO {
    @Override
    public boolean create() {
        return true;
    }

    @Override
    public Game read() {
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
