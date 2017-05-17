package com.example.jacobcovey.ticket_to_ride.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jacobcovey.ticket_to_ride.Activities.GameLobbyActivity;
import com.example.jacobcovey.ticket_to_ride.Presenters.GameOptionsPresenter;
import com.example.jacobcovey.ticket_to_ride.Presenters.IGameOptionsPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

/**
 * Created by jacobcovey on 5/16/17.
 */

public class GameOptionsFragment extends Fragment implements IGameOptionsView {

    private EditText mNameOfGameEditText;
    private EditText mNumberOfPlayersEditText;
    private Button mCreateButton;

    private IGameOptionsPresenter gameOptionsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameOptionsPresenter = new GameOptionsPresenter();
        gameOptionsPresenter.setGameOptionsView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_options_fragment, container, false);

        mNameOfGameEditText = (EditText) v.findViewById(R.id.create_name_of_game_textEdit);

        mNumberOfPlayersEditText = (EditText) v.findViewById(R.id.create_number_of_players_textEdit);

        mCreateButton = (Button) v.findViewById(R.id.create_create_button);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOptionsPresenter.createGame();
                navToGameLobbyScreenActivity();
            }
        });

        return v;
    }

    @Override
    public String getGameName() {
        return mNameOfGameEditText.getText().toString();
    }

    @Override
    public void setGameName(String gameName) {
        this.mNameOfGameEditText.setText(gameName);
    }

    @Override
    public String getNumPlayers() {
        return mNameOfGameEditText.getText().toString();
    }

    @Override
    public void setNumPlayers(String numPlayers) {
        this.mNumberOfPlayersEditText.setText(numPlayers);
    }

    public void navToGameLobbyScreenActivity() {
        Intent intent = new Intent(getActivity(), GameLobbyActivity.class);
        startActivity(intent);
    }
}
