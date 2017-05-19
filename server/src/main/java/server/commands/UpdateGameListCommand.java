package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

public class UpdateGameListCommand implements iCommand {

    @Override
    public List<CommandData> execute() {
        List<Game> gameList = ServerFacade._instance.getFilteredGamesList();
        CommandData commandData = new CommandData(CommandData.Type.UPDATEGAMELIST, gameList);
        List<CommandData> commands = new ArrayList<CommandData>();
        commands.add(commandData);
        return commands;
    }
}
