package server.commands;

import java.util.ArrayList;
import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by Riley on 5/17/2017.
 */

public class UpdateCurrentGameCommand extends BaseCommand implements iCommand {
    private Game mGame;

    public Game getGame() {
        return mGame;
    }

    public void setGame(Game game) {
        mGame = game;
    }

    public UpdateCurrentGameCommand(CommandData data) {
        mGame = (Game) data.getData();
    }

    @Override
    public List<CommandData> execute() {
        Game g =  serverFacade.getGameById(mGame.getId());
        CommandData result;
        if (g == null) {
            result = new CommandData(CommandData.Type.ERROR, "Game not found");
        } else {
            result = new CommandData(CommandData.Type.UPDATECURRENTGAME, g);
        }
        List<CommandData> dList = new ArrayList<CommandData>() ;
        dList.add(result);
        return dList;

    }
}
