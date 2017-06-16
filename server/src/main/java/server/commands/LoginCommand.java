package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.CommandData;
import shared.classes.CommandData.Type;
import shared.classes.Game;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class LoginCommand implements iCommand {
    private User data;

    public LoginCommand(CommandData data) {


    }

    @Override
    public List<CommandData> execute() {
//        authenticate
        User myUser = ServerFacade._instance.authenticateUser(data.getUsername(), data.getPassword());
        ArrayList<CommandData> dList = new ArrayList<>();

        // if the user is in the database then send client the user object
        if (myUser == null) {
            // else send a failure message
            CommandData unSuccessCmd = new CommandData(Type.ERROR, "UNSUCCESSFUL LOGIN");
            dList.add(unSuccessCmd);

        } else {
            // send the userobject to the client
            CommandData successCmd = new CommandData(Type.LOGINSUCCESSFUL, myUser);
            dList.add(successCmd);
            Game usersGame = ServerFacade._instance.findGameWithUser(myUser);
            if (usersGame != null) {
                successCmd = new CommandData(Type.GAMEJOINED, usersGame);
                dList.add(successCmd);
                GameInfo gameInfo = ServerFacade._instance.getGameInfo(usersGame.getId());
                if (gameInfo != null) {
                    successCmd = new CommandData(Type.UPDATEGAMEINFO, gameInfo);
                    dList.add(successCmd);
                }
            }

        }
        return dList;
    }

    public User getUser() {
        return data;
    }

    public void setUser(User user) {
        data = user;
    }
}
