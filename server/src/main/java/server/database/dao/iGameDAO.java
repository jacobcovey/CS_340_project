package server.database.dao;

import shared.classes.Game;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iGameDAO {
    public boolean create();
    public Game read();
    public boolean update();
    public boolean delete();
}
