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
        List<CommandData> commands = ServerFacade._instance.getCommandsForUser(data);
        List<CommandData> dList = new ArrayList<>(commands);
        commands.clear();
        return dList;
    }

}
