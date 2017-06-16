package server.database.dao.nosql;

import server.database.dao.iCommandDAO;
import shared.classes.CommandData;
import server.database.dao.iCommandDAO;
import java.util.List;
import java.util.Set;


/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLCommandDAO implements iCommandDAO {

    @Override
    public boolean create(CommandData commandData) {
        return false;
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
