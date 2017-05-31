package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.ChatView;
import com.example.jacobcovey.Views.IChatView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.ChatMessage;

/**
 * Created by spencer on 5/30/17.
 */

public class ChatPresenter implements IChatPresenter, Observer {

    private ClientPresenterFacade cpf;

    private IChatView chatView;

    public ChatPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void sendChatMessage(String message) {
        String name = cpf.getCurrentPlayer().getUserName();
        ChatMessage chatMessage = new ChatMessage(name, message);
        try {
            cpf.sendChatMessage(chatMessage);
        } catch (IOException e) {
            // TODO toast or something?
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    public void setChatView(IChatView chatView) {
        this.chatView = chatView;
    }

    public List<ChatMessage> getChatMessages() {
        return cpf.getChat().getMessages();
    }

}
