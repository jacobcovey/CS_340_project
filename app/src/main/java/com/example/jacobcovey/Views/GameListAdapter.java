package com.example.jacobcovey.Views;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacobcovey.Activities.GameLobbyActivity;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private List<Game> games;
    private GameListAdapter.ViewHolder viewHolder;
    private iGameListView parent;


    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mGameNameTextView;
        public TextView mNumberOfPlayersTextView;
        public TextView mCreatorTextView;
        public Game game;
        public iGameListView parentView;


        public ViewHolder(View view) {
            super(view);

            mGameNameTextView = (TextView) view.findViewById(R.id.game_name_textview);
            mNumberOfPlayersTextView = (TextView) view.findViewById(R.id.game_number_of_players_textview);
            mCreatorTextView = (TextView) view.findViewById(R.id.game_created_by_textview);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (parentView != null) {
                parentView.joinGame(game);
            }
        }
    }

    public GameListAdapter(List<Game> games) {
        this.games = games;
    }

    public void updateGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_game, parent, false);
        view.setBackground(parent.getResources().getDrawable(R.drawable.border));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game currentGame = games.get(position);
        viewHolder = (GameListAdapter.ViewHolder)holder;
        viewHolder.mCreatorTextView.setText(currentGame.getOwner().getUsername());
        viewHolder.mNumberOfPlayersTextView.setText( Integer.toString(currentGame.getPlayers().size()) + "/" +  Integer.toString(currentGame.getPlayerLimit()) );
        viewHolder.mGameNameTextView.setText(currentGame.getName());
        viewHolder.game = currentGame;
        viewHolder.parentView = parent;
    }


    @Override
    public int getItemCount() {
        if (games == null) {
            return  0;
        }
        return games.size();
    }

    public void setParent(iGameListView parent) {
        this.parent = parent;
        if (viewHolder != null) {
            viewHolder.parentView = parent;
        }
    }
}
