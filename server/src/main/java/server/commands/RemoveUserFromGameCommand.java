package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.CommandData.Type;
import shared.classes.Game;
import shared.classes.User;

/**
 * Created by Riley on 5/17/2017.
 */

public class RemoveUserFromGameCommand {
    private User mUser;
    private Game mGame;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }

    public List<CommandData> execute() {
        ServerFacade myFacade = new ServerFacade();
        Game mygame = myFacade.removeUserFromGame(mGame, mUser);
        ArrayList<CommandData> dList = new ArrayList<>();

        if (mygame != null) {
            CommandData successCmd = new CommandData(Type.GAMELEFT, mygame);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(Type.ERROR, "UNABLE TO REMOVE PLAYER FROM GAME");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}
