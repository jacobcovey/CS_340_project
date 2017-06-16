package server.database.dao;

import java.util.List;
import java.util.Set;

import shared.classes.Game;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iGameDAO {
    public boolean create();
    public List<Game> read();
    public boolean update();
    public boolean delete();
}
