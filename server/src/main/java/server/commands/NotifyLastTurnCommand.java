package server.commands;

import java.io.IOException;
import java.util.List;

import shared.classes.CommandData;
import shared.interfaces.iCommand;

/**
 * Created by spencer on 6/6/17.
 */

public class NotifyLastTurnCommand implements iCommand {

    @Override
    public List<CommandData> execute() throws IOException {
        // TODO update the UI somehow to show that this is the last turn of the game
        return null;
    }
}
