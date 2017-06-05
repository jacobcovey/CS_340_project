package com.example.jacobcovey.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jacobcovey.Activities.GameLobbyActivity;
import com.example.jacobcovey.Presenters.GameOptionsPresenter;
import com.example.jacobcovey.Presenters.iGameOptionsPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

/**
 * Created by jacobcovey on 5/16/17.
 */

public class GameOptionsView extends Fragment implements iGameOptionsView {

    private EditText mNameOfGameEditText;
//    private EditText mNumberOfPlayersEditText;
    private Spinner mNumberOfPlayersSpinner;
    private Button mCreateButton;

    private iGameOptionsPresenter gameOptionsPresenter;

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

//        mNumberOfPlayersEditText = (EditText) v.findViewById(R.id.create_number_of_players_textEdit);

        mNumberOfPlayersSpinner = (Spinner) v.findViewById(R.id.create_number_of_players_spinners);

        mCreateButton = (Button) v.findViewById(R.id.create_create_button);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOptionsPresenter.createGame();
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
        return mNumberOfPlayersSpinner.getSelectedItem().toString();
    }

    @Override
    public void setNumPlayers(String numPlayers) {
        this.mNumberOfPlayersSpinner.setSelection(0);
    }

    @Override
    public void navToGameLobbyScreenActivity() {
        Intent intent = new Intent(getActivity(), GameLobbyActivity.class);
        startActivity(intent);
    }
}
