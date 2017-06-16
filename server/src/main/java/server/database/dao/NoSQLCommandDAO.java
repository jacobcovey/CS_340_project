package server.database.dao;

import java.util.List;
import java.util.Set;

import shared.classes.CommandData;
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
    public List<CommandData> read() {
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
