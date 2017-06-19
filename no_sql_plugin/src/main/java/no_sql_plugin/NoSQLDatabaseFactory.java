package no_sql_plugin;

import no_sql_plugin.dao.NoSQLCommandDAO;
import no_sql_plugin.dao.NoSQLGameDAO;
import no_sql_plugin.dao.NoSQLUserDAO;
import shared.interfaces.dao.iCommandDAO;
import shared.interfaces.dao.iGameDAO;
import shared.interfaces.dao.iUserDAO;
import shared.interfaces.iDatabaseFactory;

/**
 * Created by Dylan on 6/15/2017.
 */

public class NoSQLDatabaseFactory implements iDatabaseFactory {

    private iGameDAO GameDAO;
    private iUserDAO UserDAO;
    private iCommandDAO CommandDAO;

    public NoSQLDatabaseFactory() {
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
