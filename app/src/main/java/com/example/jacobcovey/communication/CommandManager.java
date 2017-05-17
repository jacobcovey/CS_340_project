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
                return new LoginSuccessfulCommand((User) data.getData());
            case GAMEJOINED:
                return new GameJoinedCommand((Game) data.getData());
            case GAMELEFT:
                return new GameLeftCommand(data.getData());
            case GAMESTARTED:
                return new GameStartedCommand((Game) data.getData());
            case UPDATEGAMELIST:
                return new UpdateGameListCommand((List<Game>) data.getData());
            case UPDATECURRENTGAME:
                return new UpdateCurrentGameCommand((Game) data.getData());
            default:
                return null;
        }

    }
}
