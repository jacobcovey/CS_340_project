package server.commands;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import shared.interfaces.iCommandManager;

public class CommandManager implements iCommandManager {

    @Override
    public iCommand createCommand(CommandData data) {
        CommandData.Type type = data.getType();
        switch(type) {
            case CREATEGAME:
                return new CreateGameCommand(data);
            case JOINGAME:
                return new JoinGameCommand(data);
            case LEAVEGAME:
                return new LeaveGameCommand(data);
            case LOGIN:
                return new LoginCommand(data);
            case REGISTER:
                return new RegisterCommand(data);
            case STARTGAME:
                return new StartGameCommand(data);
            case UPDATECURRENTGAME:
                return new UpdateCurrentGameCommand(data);
            case UPDATEGAMELIST:
                return new UpdateGameListCommand(data);
            default:
                return null;
        }
    }
}
