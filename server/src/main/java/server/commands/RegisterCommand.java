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

public class RegisterCommand extends BaseCommand implements iCommand {
    private User mUser;

    public RegisterCommand(CommandData data) {
        mUser = (User) data.getData();
    }

    public List<CommandData> execute() {
        // authenticate
        User myUser = serverFacade.getUserByUserName(mUser.getUsername(), mUser.getPassword());
        ArrayList<CommandData> dList = new ArrayList<>();


        // if the user is in the database then send client the user object
        if (myUser == null) {
            // make the new user and return user object to client
            serverFacade.addUser(mUser);
            //send myuser to the client
            CommandData successCmd = new CommandData(Type.LOGINSUCCESSFUL, mUser);
            dList.add(successCmd);
        } else {
            // send the already registered message to client
            CommandData unSuccessCmd = new CommandData(Type.ERROR, "User already exists");
            dList.add(unSuccessCmd);
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
