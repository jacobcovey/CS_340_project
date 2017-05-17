package com.example.jacobcovey.communication;

import com.example.jacobcovey.commands.GameJoinedCommand;
import com.example.jacobcovey.commands.GameLeftCommand;
import com.example.jacobcovey.commands.GameStartedCommand;
import com.example.jacobcovey.commands.LoginSuccessfulCommand;
import com.example.jacobcovey.commands.UpdateCurrentGameCommand;
import com.example.jacobcovey.commands.UpdateGameListCommand;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.User;
import shared.interfaces.iCommand;
import shared.interfaces.iCommandManager;

/**
 * Created by billrichards on 5/17/17.
 */

public class CommandManager implements iCommandManager {

    public static CommandManager _instance = new CommandManager();

    private CommandManager() {}

    @Override
    public iCommand createCommand(CommandData data) {

        switch (data.getType()) {
            case LOGINSUCCESSFUL:
                return new LoginSuccessfulCommand(data);
            case GAMEJOINED:
                return new GameJoinedCommand(data);
            case GAMELEFT:
                return new GameLeftCommand(data);
            case GAMESTARTED:
                return new GameStartedCommand(data);
            case UPDATEGAMELIST:
                return new UpdateGameListCommand(data);
            case UPDATECURRENTGAME:
                return new UpdateCurrentGameCommand(data);
            default:
                return null;
        }

    }
}
