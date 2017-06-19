package shared.interfaces.dao;

import shared.classes.CommandData;

import java.util.List;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iCommandDAO {
    public boolean create(CommandData commandData);
    public List<CommandData> read();
    public boolean update();
    public boolean delete(String id);
    public boolean clear();
}
