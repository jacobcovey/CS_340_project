package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.ChatMessage;
import shared.classes.CommandData;
import shared.classes.HistoryAction;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/31/2017.
 */

public class SendHistory implements iCommand {
    HistoryAction data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        HistoryAction action = data;
        ServerFacade._instance.getGameInfo(gameId).getHistory().addAction(data);

        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEHISTORY, action), gameId);
        ArrayList<CommandData> dList = new ArrayList<>();

        if (action != null) {
            return dList;
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO SEND CHAT");
            dList.add(unSuccessCmd);
        }
        return dList;

    }
}
