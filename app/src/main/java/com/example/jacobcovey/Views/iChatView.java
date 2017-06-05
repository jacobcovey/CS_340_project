package com.example.jacobcovey.Views;

import java.util.List;

import shared.classes.ChatMessage;

/**
 * Created by spencer on 5/29/17.
 */

public interface iChatView {

    void setChatMessages(List<ChatMessage> chatMessages);
}
