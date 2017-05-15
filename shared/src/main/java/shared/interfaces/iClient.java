package shared.interfaces;

import java.util.List;

import shared.classes.Game;
import shared.classes.User;

/**
 * Created by billrichards on 5/15/17.
 */

public interface iClient {

    public void setUser(User user);
    public void setAuthToken(String token);
    public void setGameList(List<Game> gameList);
    public void setColor(String color);
    public void setCurrentGame(Game game);

}
