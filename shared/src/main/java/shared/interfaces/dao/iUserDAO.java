package shared.interfaces.dao;

import java.util.Set;

import shared.classes.User;

/**
 * Created by Dylan on 6/15/2017.
 */

public interface iUserDAO {
    public boolean create(User user);
    public Set<User> read();
    public boolean update();
    public boolean delete();
    public boolean clear();
}
