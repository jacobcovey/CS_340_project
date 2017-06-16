package server.database.plugin;

import server.database.dao.iGameDAO;
import server.database.dao.sql.SQLCommandDAO;
import server.database.dao.sql.SQLGameDAO;
import server.database.dao.sql.SQLUserDAO;
import server.database.dao.iCommandDAO;
import server.database.dao.iUserDAO;
import server.database.iDatabaseFactory;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLDatabaseFactory implements iDatabaseFactory {

    private iGameDAO GameDAO;
    private iUserDAO UserDAO;
    private iCommandDAO CommandDAO;

    public SQLDatabaseFactory() {
        GameDAO = new SQLGameDAO();
        UserDAO = new SQLUserDAO();
        CommandDAO = new SQLCommandDAO();
    }

    @Override
    public void startTransaction() {
    }

    @Override
    public void endTransaction() {
    }

    public void rollback() {
    }

    @Override
    public void clear() {
    }

    @Override
    public iGameDAO getGameDAO() {
        return this.GameDAO;
    }

    @Override
    public iUserDAO getUserDAO() {
        return this.UserDAO;
    }

    @Override
    public iCommandDAO getCommandDAO() {
        return this.CommandDAO;
    }

}
