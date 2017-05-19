package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.CommandData.Type;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class LoginCommand implements iCommand {
    private User mUser;

    public LoginCommand(CommandData data) {
        mUser = (User) data.getData();

    }


    @Override
    public List<CommandData> execute() {
//        authenticate
        ServerFacade myFacade = new ServerFacade();
        User myUser = myFacade.authenticateUser(mUser.getUsername(), mUser.getPassword());
        ArrayList<CommandData> dList = new ArrayList<>();


        // if the user is in the database then send client the user object
        if (myUser == null) {
            // else send a failure message
            CommandData unSuccessCmd = new CommandData(Type.ERROR, "UNSUCCESSFUL LOGIN");
            dList.add(unSuccessCmd);

        } else {
            // send the userobject to the client
            CommandData successCmd = new CommandData(Type.LOGINSUCCESSFUL, mUser);
            dList.add(successCmd);

        }
        return dList;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
