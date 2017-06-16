package server.database.dao;

import shared.classes.CommandData;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLCommandDAO implements iCommandDAO {
    @Override
    public boolean create(CommandData commandData) {
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
