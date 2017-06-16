package server.database.plugin;

import server.database.SQLDatabase;
import server.database.dao.sql.SQLCommandDAO;
import server.database.dao.sql.SQLGameDAO;
import server.database.dao.sql.SQLUserDAO;
import server.database.dao.iCommandDAO;
import server.database.dao.iGameDAO;
import server.database.dao.iUserDAO;
import server.database.iDatabase;
import server.database.iPersistenceProvider;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLPlugin implements iPersistenceProvider {

    private iDatabase db = new SQLDatabase();

    private iGameDAO GameDAO;
    private iUserDAO UserDAO;
    private iCommandDAO CommandDAO;

    public SQLPlugin() {
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
