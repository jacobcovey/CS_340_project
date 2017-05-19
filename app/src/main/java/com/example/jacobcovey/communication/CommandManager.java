package com.example.jacobcovey.communication;

import com.example.jacobcovey.commands.ErrorCommand;
import com.example.jacobcovey.commands.GameJoinedCommand;
import com.example.jacobcovey.commands.GameLeftCommand;
import com.example.jacobcovey.commands.GameStartedCommand;
import com.example.jacobcovey.commands.LoginSuccessfulCommand;
import com.example.jacobcovey.commands.UpdateCurrentGameCommand;
import com.example.jacobcovey.commands.UpdateGameListCommand;
import com.google.gson.Gson;

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

    private Gson gson = new Gson();

    public static CommandManager _instance = new CommandManager();

    private CommandManager() {}

    @Override
    public iCommand createCommand(CommandData data, String s) {

        switch (data.getType()) {
            case LOGINSUCCESSFUL:
                return gson.fromJson(s, LoginSuccessfulCommand.class);
            case GAMEJOINED:
                return gson.fromJson(s, GameJoinedCommand.class);
            case GAMELEFT:
                return gson.fromJson(s, GameLeftCommand.class);
            case GAMESTARTED:
                return gson.fromJson(s, GameStartedCommand.class);
            case UPDATEGAMELIST:
                return gson.fromJson(s, UpdateGameListCommand.class);
            case UPDATECURRENTGAME:
                return gson.fromJson(s, UpdateCurrentGameCommand.class);
            case ERROR:
                return gson.fromJson(s, ErrorCommand.class);
            default:
                return null;
        }

    }
}
