package server.database.dao;

import shared.classes.CommandData;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iCommandDAO {
    public boolean create(CommandData commandData);
    public iCommand read();
    public boolean update();
    public boolean delete();
}
