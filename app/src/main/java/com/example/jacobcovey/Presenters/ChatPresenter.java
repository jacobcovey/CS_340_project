package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.ChatView;
import com.example.jacobcovey.Views.IChatView;
import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.ArrayList;
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
        SendChatMessage chatMessageAsync = new SendChatMessage();
        chatMessageAsync.execute(chatMessage);
    }

    private class SendChatMessage extends AsyncTask<ChatMessage, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(ChatMessage... params) {
            try {
                cpf.sendChatMessage(params[0]);
            } catch (IOException e) {
                System.out.printf(e.getMessage());
//                gameOptionsView.displayToast("login failed");
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        chatView.setChatMessages(cpf.getChat().getMessages());
    }

    public void setChatView(IChatView chatView) {
        this.chatView = chatView;
    }

    public List<ChatMessage> getChatMessages() {
        return cpf.getChat().getMessages();
    }

}
