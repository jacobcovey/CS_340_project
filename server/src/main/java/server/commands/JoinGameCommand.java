package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.CommandData.Type;
import shared.classes.Game;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class JoinGameCommand implements iCommand {
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

    public JoinGameCommand(CommandData data) {
        mGame = (Game) data.getData();
    }

    public List<CommandData> execute() {
        //add user to game
        ServerFacade myFacade = new ServerFacade();
        Game mygame = myFacade.addUserToGame(mGame, mUser);
        ArrayList<CommandData> dList = new ArrayList<>();

        if (mygame != null) {
            CommandData successCmd = new CommandData(Type.GAMEJOINED, mygame);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(Type.ERROR, "UNABLE TO ADD PLAYER TO GAME");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}
