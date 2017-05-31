package com.example.jacobcovey.Views;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import shared.classes.ChatMessage;

public class ChatView extends Fragment implements IChatView {

    private EditText mNewChatMessage;
    private Button mSendChatMessageButton;
    private RecyclerView mChatMessages;

    @Override
    public void setChatMessages(List<ChatMessage> chatMessages) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO update the adapter
            }
        });
    }

    @Override
    public void addChatMessage(ChatMessage chatMessages) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO update the adapter
            }
        });
    }

    @Override
    public String getNewChatMessage() {
        return mNewChatMessage.getText().toString();
    }
}
