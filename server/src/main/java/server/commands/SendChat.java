package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import shared.classes.ChatMessage;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.Player;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class SendChat implements iCommand {
    ChatMessage data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        ChatMessage message = data;
        ServerFacade._instance.getGameInfo(gameId).getChat().addMessage(data);

        for (User user : ServerFacade._instance.getUsers()) {
            ServerFacade._instance.addCommandToUser(new CommandData(CommandData.Type.UPDATECHAT, message), user.getUsername());
        }
        ArrayList<CommandData> dList = new ArrayList<>();

        if (message != null) {
            return dList;
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO SEND CHAT");
            dList.add(unSuccessCmd);
        }
        return dList;

    }
}
