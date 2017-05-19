package com.example.jacobcovey.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacobcovey.ticket_to_ride.R;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardFragment extends Fragment implements IGameBoardView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_board_fragment, container, false);


        return v;
    }

}
