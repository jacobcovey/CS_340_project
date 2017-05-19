package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

public class UpdateGameListCommand extends BaseCommand implements iCommand {

    public UpdateGameListCommand(CommandData data) {}

    @Override
    public List<CommandData> execute() {
        List<Game> gameList = serverFacade.getGameList();
        CommandData commandData = new CommandData(CommandData.Type.UPDATEGAMELIST, gameList);
        List<CommandData> commands = new ArrayList<CommandData>();
        commands.add(commandData);
        return commands;
    }
}
