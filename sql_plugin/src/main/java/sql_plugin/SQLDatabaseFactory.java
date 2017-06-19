package sql_plugin;

import shared.interfaces.dao.iGameDAO;
import sql_plugin.dao.SQLCommandDAO;
import sql_plugin.dao.SQLGameDAO;
import sql_plugin.dao.SQLUserDAO;
import shared.interfaces.dao.iCommandDAO;
import shared.interfaces.dao.iUserDAO;
import shared.interfaces.iDatabaseFactory;

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
