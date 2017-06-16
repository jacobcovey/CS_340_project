package server.database.plugin;

<<<<<<< HEAD
import server.database.SQLDatabase;
import server.database.dao.sql.SQLCommandDAO;
import server.database.dao.sql.SQLGameDAO;
import server.database.dao.sql.SQLUserDAO;
=======
import server.database.SQLDatabaseFactory;
import server.database.dao.SQLCommandDAO;
import server.database.dao.SQLGameDAO;
import server.database.dao.SQLUserDAO;
>>>>>>> e02b1e32a6e5660844f0bf1d8e72cbe41e03dc02
import server.database.dao.iCommandDAO;
import server.database.dao.iGameDAO;
import server.database.dao.iUserDAO;
import server.database.iDatabaseFactory;
import server.database.iPersistenceProvider;

/**
 * Created by Dylan on 6/15/2017.
 */

public class SQLPlugin implements iPersistenceProvider {

    private iDatabaseFactory db = new SQLDatabaseFactory();

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
