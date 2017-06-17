package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.CommandData.Type;
import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class JoinGameCommand implements iCommand {
    private GameRequest data;

    public List<CommandData> execute() {
        //add user to game
        Game game = data.getGame();
        User user = data.getUser();
        ArrayList<CommandData> dList = new ArrayList<>();
        for (User player: game.getPlayers()) {
            if (user.getUsername().equals(player)) {
                CommandData unSuccessCmd = new CommandData(Type.ERROR, "UNABLE TO ADD PLAYER TO GAME");
                dList.add(unSuccessCmd);
                return dList;
            }
        }
        Game mygame = ServerFacade._instance.addUserToGame(new server.model.Game(game), user);

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
