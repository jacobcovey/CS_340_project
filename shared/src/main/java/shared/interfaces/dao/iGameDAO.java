package shared.interfaces.dao;

import java.util.List;

import shared.classes.Game;


/**
 * Created by Dylan on 6/15/2017.
 */

public interface iGameDAO {
    public boolean create(Game game);
    public List<Game> read();
    public boolean update();
    public boolean delete(String id);
    public boolean clear();
}
