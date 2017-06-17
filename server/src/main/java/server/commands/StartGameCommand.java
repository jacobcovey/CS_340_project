package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.model.GameInfo;
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
        if (ServerFacade._instance.getGameInfo(gameId) == null) {
            ServerFacade._instance.addGameInfo(new server.model.Game(data));
        }
        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);

        List<CommandData> result = new ArrayList<>();
        result.add(new CommandData(CommandData.Type.GAMESTARTED, gameInfo));
        return result;
    }
}
