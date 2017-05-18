package com.example.jacobcovey.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/17/17.
 */

class GameListAdapter extends RecyclerView.Adapter {

    private List<Game> games;
//    private Game game;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mGameNameTextView;
        public TextView mNumberOfPlayersTextView;
        public TextView mCreatorTextView;
        public Game game;
        public ViewHolder(View view) {
            super(view);

            mGameNameTextView = (TextView) view.findViewById(R.id.game_name_textview);
            mNumberOfPlayersTextView = (TextView) view.findViewById(R.id.game_number_of_players_textview);
            mCreatorTextView = (TextView) view.findViewById(R.id.game_created_by_textview);

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("Clicked");
                }
            });

        }

    }

    public GameListAdapter(List<Game> games) {
        this.games = games;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_game, parent, false);
        view.setBackground(parent.getResources().getDrawable(R.drawable.border));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Game currentGame = games.get(position);
        GameListAdapter.ViewHolder vh = (GameListAdapter.ViewHolder)holder;
        vh.mCreatorTextView.setText(currentGame.getOwner().getUsername());
        vh.mNumberOfPlayersTextView.setText( Integer.toString(currentGame.getPlayers().size()) + "/" + Integer.toString(games.size()) );
        vh.mGameNameTextView.setText(currentGame.getName());
        vh.game = currentGame;
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
