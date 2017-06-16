package com.example;

import server.database.iCommandDAORP;

/**
 * Created by Riley on 6/16/2017.
 */

public class SQLCommandDAORP implements iCommandDAORP {
    @Override
    public void create() {
        System.out.println("Created SQL Command DB");
    }
}
