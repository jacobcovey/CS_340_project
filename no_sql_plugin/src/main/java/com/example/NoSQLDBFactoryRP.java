package com.example;

import server.database.dao.iCommandDAO;
import server.database.dao.iGameDAO;
import server.database.dao.iUserDAO;
import server.database.dao.nosql.NoSQLCommandDAO;
import server.database.dao.nosql.NoSQLGameDAO;
import server.database.dao.nosql.NoSQLUserDAO;
import server.database.iPersistenceProvider;

public class NoSQLDBFactoryRP implements iPersistenceProvider {
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
        return new NoSQLGameDAO();
    }

    @Override
    public iUserDAO getUserDAO() {
        return new NoSQLUserDAO();
    }

    @Override
    public iCommandDAO getCommandDAO() {
        return new NoSQLCommandDAO();
    }

    @Override
    public void rollback() {

    }
}
