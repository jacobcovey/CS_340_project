package shared.interfaces;

import shared.interfaces.dao.iCommandDAO;
import shared.interfaces.dao.iGameDAO;
import shared.interfaces.dao.iUserDAO;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iDatabaseFactory {

        public void startTransaction();

        public void endTransaction();

        public void clear();

        public iGameDAO getGameDAO();

        public iUserDAO getUserDAO();

        public iCommandDAO getCommandDAO();

        public void rollback();
}
