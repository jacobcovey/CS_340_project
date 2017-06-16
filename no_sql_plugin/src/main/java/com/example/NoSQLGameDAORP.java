package com.example;

import server.database.iGameDAORP;

/**
 * Created by Riley on 6/16/2017.
 */

public class NoSQLGameDAORP implements iGameDAORP {
    @Override
    public void create() {
        System.out.println("Created No SQL Game DB");
    }
}
