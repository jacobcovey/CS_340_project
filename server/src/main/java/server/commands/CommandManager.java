package server.commands;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import shared.interfaces.iCommandManager;

public class CommandManager implements iCommandManager {

    @Override
    public iCommand createCommand(CommandData data) {
        CommandData.Type type = data.getType();
        switch(type) {
            case LOGIN:
                return new LoginCommand(data);
            case REGISTER:
                return null;
            case JOINGAME:
                return null;
            case CREATEGAME:
                return null;
            case STARTGAME:
                return null;
            case LEAVEGAME:
                return null;
            default:
                return null;
        }
    }
}
