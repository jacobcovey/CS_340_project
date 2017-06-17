package server.database.dao;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import java.util.List;
import java.util.Set;
import shared.classes.CommandData;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iCommandDAO {
    public boolean create(CommandData commandData);
    public List<CommandData> read(String id);
    public boolean update();
    public boolean delete(String id);
}
