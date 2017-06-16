package server.database.dao.nosql;

import server.database.dao.iCommandDAO;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLCommandDAO implements iCommandDAO {
    @Override
    public boolean create() {
        return true;
    }

    @Override
    public iCommand read() {
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
