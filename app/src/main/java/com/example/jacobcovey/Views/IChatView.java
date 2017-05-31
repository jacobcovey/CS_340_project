package com.example.jacobcovey.Views;

import java.util.List;

import shared.classes.ChatMessage;

/**
 * Created by spencer on 5/29/17.
 */

interface IChatView {

    void setChatMessages(List<ChatMessage> chatMessages);
    void addChatMessage(ChatMessage chatMessages);
    String getNewChatMessage();
}
