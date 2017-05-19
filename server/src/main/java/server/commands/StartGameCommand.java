package server.commands;

import java.util.ArrayList;
import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class StartGameCommand implements iCommand {
    private Game mGame;

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }

    public StartGameCommand(CommandData data) {
    }

    @Override
    public List<CommandData> execute() {
        List<CommandData> result = new ArrayList<>();
        result.add(new CommandData(CommandData.Type.GAMESTARTED, null));
        return result;
    }
}
