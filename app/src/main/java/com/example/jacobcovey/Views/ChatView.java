package com.example.jacobcovey.Views;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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

    private EditText chatEditText; // TODO
    private Button sendButton; // TODO
    private Button dissmissButton;
    private RecyclerView chatMessages;
    private ChatPresenter chatPresenter;
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private LinearLayoutManager manager;

    private ChatDrawerContainer chatDrawerContainer;

    public interface ChatDrawerContainer {
        void closeChatDrawer();
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
        sendButton = (Button) v.findViewById(R.id.send_chat_button);
        chatEditText = (EditText) v.findViewById(R.id.new_chat_box);

        dissmissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatDrawerContainer.closeChatDrawer();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.sendChatMessage(chatEditText.getText().toString());
            }
        });

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        List<ChatMessage> messages = chatPresenter.getChatMessages();
        adapter = new ChatAdapter(messages);
        recyclerView = (RecyclerView) v.findViewById(R.id.chat_recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        return v;
    }

    @Override
    public void setChatMessages(final List<ChatMessage> chatMessages) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setMessages(chatMessages);
            }
        });
    }

    @Override
    public void addChatMessage(final ChatMessage chatMessage) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addMessage(chatMessage);
            }
        });
    }
}
