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

public class LeaveGameCommand implements iCommand {

    private User mUser;
    private Game mGame;

    public LeaveGameCommand(CommandData data) {
        mGame = (Game) data.getData();
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
