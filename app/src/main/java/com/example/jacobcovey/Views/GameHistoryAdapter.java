package com.example.jacobcovey.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacobcovey.ticket_to_ride.R;

import shared.classes.History;
import shared.classes.HistoryAction;

/**
 * Created by jacobcovey on 5/31/17.
 */

public class GameHistoryAdapter extends RecyclerView.Adapter {

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView historyMessage;

        public ViewHolder (View view) {
            super(view);
            historyMessage = (TextView) view.findViewById(R.id.history_text_textview);
        }
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public void addHistoryActtion(HistoryAction historyAction) {
        history.addAction(historyAction);
    }

    private History history;

    public GameHistoryAdapter(History history) {
        this.history = history;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_history, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryAction activity = history.getActions().get(position);
        GameHistoryAdapter.ViewHolder vh = (GameHistoryAdapter.ViewHolder) holder;
        vh.historyMessage.setText(activity.toString());
    }

    @Override
    public int getItemCount() {
        if (history.getActions() == null) {
            return 0;
        } else {
            return history.getActions().size();
        }
    }
}
