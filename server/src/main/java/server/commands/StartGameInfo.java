package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/31/2017.
 */

public class StartGameInfo implements iCommand {
    private Game data;

    public StartGameInfo(CommandData data) {
    }

    @Override
    public List<CommandData> execute() {
        ServerFacade._instance.addGameInfo(data);
        List<CommandData> result = new ArrayList<>();
        result.add(new CommandData(CommandData.Type.STARTGAMEINFO, ServerFacade._instance.getGameInfo(data.getId())));
        return result;
    }
}
