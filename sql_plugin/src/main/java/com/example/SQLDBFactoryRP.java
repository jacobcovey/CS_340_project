package com.example;

import server.database.dao.iCommandDAO;
import server.database.dao.iGameDAO;
import server.database.dao.iUserDAO;
import server.database.dao.sql.SQLCommandDAO;
import server.database.dao.sql.SQLGameDAO;
import server.database.dao.sql.SQLUserDAO;
import server.database.iPersistenceProvider;

public class SQLDBFactoryRP implements iPersistenceProvider {
    @Override
    public void startTransaction() {

    }

    @Override
    public void endTransaction() {

    }

    @Override
    public void clear() {

    }

    @Override
    public iGameDAO getGameDAO() {
        return new SQLGameDAO();
    }

    @Override
    public iUserDAO getUserDAO() {
        return new SQLUserDAO();
    }

    @Override
    public iCommandDAO getCommandDAO() {
        return new SQLCommandDAO();
    }

    @Override
    public void rollback() {

    }
}
