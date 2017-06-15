package server.database;

import server.database.dao.iCommandDAO;
import server.database.dao.iGameDAO;
import server.database.dao.iUserDAO;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iPersistenceProvider {

        public void startTransaction();

        public void endTransaction();

        public void clear();

        public iGameDAO getGameDAO();

        public iUserDAO getUserDAO();

        public iCommandDAO getCommandDAO();

        public void rollback();
}
