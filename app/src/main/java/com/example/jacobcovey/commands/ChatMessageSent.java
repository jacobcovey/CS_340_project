package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.ChatMessage;
import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/25/2017.
 */

public class ChatMessageSent implements iCommand {

    ChatMessage data;

    public ChatMessageSent(CommandData data) {
        this.data = (ChatMessage) data.getData();
    }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.getGameInfo().getChat().addMessage(data);
        return null;
    }
}