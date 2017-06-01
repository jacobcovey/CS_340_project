package com.example.jacobcovey.communication;

import com.example.jacobcovey.commands.DestinationCardDrawn;
import com.example.jacobcovey.commands.DestinationCardPicked;
import com.example.jacobcovey.commands.ErrorCommand;
import com.example.jacobcovey.commands.FaceDownTrainCardPicked;
import com.example.jacobcovey.commands.FaceUpTrainCardPicked;
import com.example.jacobcovey.commands.GameJoinedCommand;
import com.example.jacobcovey.commands.GameLeftCommand;
import com.example.jacobcovey.commands.GameStartedCommand;
import com.example.jacobcovey.commands.LoginSuccessfulCommand;
import com.example.jacobcovey.commands.RouteClaimed;
import com.example.jacobcovey.commands.UpdateChat;
import com.example.jacobcovey.commands.UpdateCurrentGameCommand;
import com.example.jacobcovey.commands.UpdateFaceUpTrainCardDeck;
import com.example.jacobcovey.commands.UpdateGameListCommand;
import com.example.jacobcovey.commands.UpdateHistory;
import com.google.gson.Gson;

import shared.classes.CommandData;
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
            case FACEDOWNTRAINCARDPICKED:
                return gson.fromJson(s, FaceDownTrainCardPicked.class);
            case FACEUPTRAINCARDPICKED:
                return gson.fromJson(s, FaceUpTrainCardPicked.class);
            case ROUTECLAIMED:
                return gson.fromJson(s, RouteClaimed.class);
            case UPDATECHAT:
                return gson.fromJson(s, UpdateChat.class);
            case UPDATEHISTORY:
                return gson.fromJson(s, UpdateHistory.class);
            case UPDATEFACEUPTRAINCARDDECK:
                return gson.fromJson(s, UpdateFaceUpTrainCardDeck.class);
            case DESTINATIONCARDDRAWN:
                return gson.fromJson(s, DestinationCardDrawn.class);
            case DESTINATIONCARDSPICKED:
                return gson.fromJson(s, DestinationCardPicked.class);
            case ERROR:
                return gson.fromJson(s, ErrorCommand.class);
            default:
                return null;
        }

    }
}
