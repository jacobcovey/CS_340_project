package com.example.jacobcovey.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jacobcovey.Activities.GameLobbyActivity;
import com.example.jacobcovey.Activities.GameOptionsActivity;
import com.example.jacobcovey.Presenters.GameListPresenter;
import com.example.jacobcovey.Presenters.iGameListPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class GameListView extends Fragment implements iGameListView {

    private Button mCreateGameButton;

    private RecyclerView mGamesRecyclerView;
    private RecyclerView.Adapter mGamesAdapter;
    private RecyclerView.LayoutManager mGamesLayoutManager;

    private iGameListPresenter gameListPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameListPresenter = new GameListPresenter();
        gameListPresenter.setGameListView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_list_fragment, container, false);

        mCreateGameButton = (Button) v.findViewById(R.id.gameList_create_game_button);
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameListPresenter.setUpGame();
            }
        });

        mGamesRecyclerView = (RecyclerView) v.findViewById(R.id.games_recyclerView);

        mGamesRecyclerView.setHasFixedSize(true);

        mGamesLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager)mGamesLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mGamesRecyclerView.setLayoutManager(mGamesLayoutManager);

        List<Game> games = gameListPresenter.getGames();

        GameListAdapter adapter = new GameListAdapter(games);
        adapter.setParent(this);

        mGamesRecyclerView.setAdapter(adapter);


        return v;
    }

    @Override
    public void updateGameList(final List<Game> games) {

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                GameListAdapter gameListAdapter = (GameListAdapter) mGamesRecyclerView.getAdapter();
                gameListAdapter.updateGames(games);
                gameListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void navToGameOptionsScreenActivity() {
        Intent intent = new Intent(getActivity(), GameOptionsActivity.class);
        startActivity(intent);
    }

    @Override
    public void navToGameLobbyScreenActivity() {
        Intent intent = new Intent(getActivity(), GameLobbyActivity.class);
        startActivity(intent);
    }

    @Override
    public void joinGame(Game game) {
        gameListPresenter.joinGame(game);
    }

    @Override
    public void setList(List<Object> list) {

    }

    @Override
    public void displayToast(final String message) {

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getActivity(), message,
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}
