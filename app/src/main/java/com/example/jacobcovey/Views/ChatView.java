package com.example.jacobcovey.Views;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jacobcovey.Presenters.ChatPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

import shared.classes.ChatMessage;

public class ChatView extends Fragment implements IChatView {

    private EditText newChatMessage; // TODO
    private Button sendChatMessageButton; // TODO
    private Button dissmissButton;
    private RecyclerView chatMessages;
    private ChatPresenter chatPresenter;

    private ChatDrawerContainer chatDrawerContainer;

    public interface ChatDrawerContainer {
        public void closeChatDrawer();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            chatDrawerContainer = (ChatDrawerContainer) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "Must implement ChatDrawerContainer");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatPresenter = new ChatPresenter();
        chatPresenter.setChatView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_fragment, container, false);
        dissmissButton = (Button) v.findViewById(R.id.chat_dismiss_button);

        dissmissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatDrawerContainer.closeChatDrawer();
            }
        });
        return v;
    }

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
        return newChatMessage.getText().toString();
    }
}
