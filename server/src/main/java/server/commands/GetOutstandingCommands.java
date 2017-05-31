package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.model.ServerModelRoot;
import shared.classes.CommandData;
import shared.classes.Player;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class GetOutstandingCommands implements iCommand {
    String data;

    public List<CommandData> execute() {
        List<CommandData> commands = ServerModelRoot.getInstance().getCommandsForUser(data);
        ArrayList<CommandData> dList = new ArrayList<>();

        if (commands != null) {
            CommandData successCmd = new CommandData(CommandData.Type.GETOUTSTANDINGCOMMANDS, commands);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO GET OUTSTANDINGCOMMANDS");
            dList.add(unSuccessCmd);
        }
        return dList;

    }

}
