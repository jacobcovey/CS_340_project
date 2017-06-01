package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class StartGameCommand implements iCommand {
    private Game data;
    private String gameId;
    private String userName;


    public StartGameCommand(CommandData data) {
    }

    @Override
    public List<CommandData> execute() {
        ServerFacade._instance.initializeCities();
        ServerFacade._instance.addGameInfo(data);
        List<CommandData> result = new ArrayList<>();
        result.add(new CommandData(CommandData.Type.GAMESTARTED, ServerFacade._instance.getGameInfo(gameId)));
        return result;
    }
}
