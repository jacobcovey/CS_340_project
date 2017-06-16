package server.database.dao;

import shared.interfaces.iCommand;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iCommandDAO {
    public boolean create();
    public iCommand read();
    public boolean update();
    public boolean delete();
}
