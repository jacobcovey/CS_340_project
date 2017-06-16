package server.database.dao;

import shared.classes.User;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iUserDAO {
    public boolean create();
    public User read();
    public boolean update();
    public boolean delete();
}
