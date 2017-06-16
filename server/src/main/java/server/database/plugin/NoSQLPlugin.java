package server.database.plugin;

import server.database.iDatabaseFactory;
import server.database.dao.nosql.NoSQLCommandDAO;
import server.database.dao.nosql.NoSQLGameDAO;
import server.database.dao.nosql.NoSQLUserDAO;
import server.database.NoSQLDatabaseFactory;
import server.database.dao.iCommandDAO;
import server.database.dao.iGameDAO;
import server.database.dao.iUserDAO;
import server.database.iDatabaseFactory;
import server.database.iPersistenceProvider;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLPlugin implements iPersistenceProvider {
    private iDatabaseFactory db = new NoSQLDatabaseFactory();

    private iGameDAO GameDAO;
    private iUserDAO UserDAO;
    private iCommandDAO CommandDAO;

    public NoSQLPlugin() {
        GameDAO = new NoSQLGameDAO();
        UserDAO = new NoSQLUserDAO();
        CommandDAO = new NoSQLCommandDAO();
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
