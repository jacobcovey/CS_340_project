package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.CommandData.Type;
import shared.classes.Game;
import shared.classes.User;

/**
 * Created by Riley on 5/18/2017.
 */

public class CreateGameCommand {
    private Game mGame;
    private User mCreator;

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }

    public User getCreator() {
        return mCreator;
    }

    public void setCreator(User creator) {
        mCreator = creator;
    }

    public List<CommandData> execute() {
        ServerFacade myFacade = new ServerFacade();
        myFacade.addGame(mGame);
        Game mygame = myFacade.addUserToGame(mGame, mCreator);
        ArrayList<CommandData> dList = new ArrayList<>();


        if (mygame != null) {
            CommandData successCmd = new CommandData(Type.UPDATEGAMELIST, mygame);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(Type.ERROR, "FAILED TO CREATE GAME");
            dList.add(unSuccessCmd);

        }
        return dList;

    }
}
