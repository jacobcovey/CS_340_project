package com.example.jacobcovey.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

import shared.classes.ChatMessage;

/**
 * Created by spencer on 5/30/17.
 */

public class ChatAdapter extends RecyclerView.Adapter {

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public TextView message;

        public ViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.chat_item_username);
            message = (TextView) view.findViewById(R.id.chat_item_message);
        }
    }

    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_chat, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage currentMessage = messages.get(position);
        ChatAdapter.ViewHolder vh = (ChatAdapter.ViewHolder) holder;
        vh.userName.setText(currentMessage.getUserName());
        vh.message.setText(currentMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        if (messages == null) {
            return 0;
        }
        return messages.size();
    }
}
