package com.example.jacobcovey.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jacobcovey.Activities.GameLobbyActivity;
import com.example.jacobcovey.Activities.GameOptionsActivity;
import com.example.jacobcovey.Presenters.GameListPresenter;
import com.example.jacobcovey.Presenters.IGameListPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class GameListFragment extends Fragment implements IGameListView {

    private Button mCreateGameButton;

    private IGameListPresenter gameListPresenter;

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
                navToGameOptionsScreenActivity();
            }
        });

        return v;
    }

    public void navToGameOptionsScreenActivity() {
        Intent intent = new Intent(getActivity(), GameOptionsActivity.class);
        startActivity(intent);
    }

    public void navToGameLobbyScreenActivity() {
        Intent intent = new Intent(getActivity(), GameLobbyActivity.class);
        startActivity(intent);
    }

    @Override
    public void setList(List<Object> list) {

    }
}
