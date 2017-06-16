package com.example;

import server.database.iUserDAORP;

/**
 * Created by Riley on 6/16/2017.
 */

public class NoSQLUserDAORP implements iUserDAORP {
    @Override
    public void create() {
        System.out.println("Created No SQL User DB");
    }
}
