package com.example;

import server.database.iUserDAORP;

/**
 * Created by Riley on 6/16/2017.
 */

public class SQLUserDAORP implements iUserDAORP {
    @Override
    public void create() {
        System.out.println("Created SQL USER DB");
    }
}
