package server.commands;

import com.google.gson.Gson;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import shared.interfaces.iCommandManager;

public class CommandManager implements iCommandManager {

    private Gson gson = new Gson();

    @Override
    public iCommand createCommand(CommandData data, String s) {
        CommandData.Type type = data.getType();
        switch(type) {
            case CREATEGAME:
                return gson.fromJson(s, CreateGameCommand.class);
            case JOINGAME:
                return gson.fromJson(s, JoinGameCommand.class);
            case LEAVEGAME:
                return gson.fromJson(s, LeaveGameCommand.class);
            case LOGIN:
                return gson.fromJson(s, LoginCommand.class);
            case REGISTER:
                return gson.fromJson(s, RegisterCommand.class);
            case STARTGAME:
                return gson.fromJson(s, StartGameCommand.class);
            case UPDATECURRENTGAME:
                return gson.fromJson(s, UpdateCurrentGameCommand.class);
            case UPDATEGAMELIST:
                return gson.fromJson(s, UpdateGameListCommand.class);
            default:
                return null;
        }
    }
}
