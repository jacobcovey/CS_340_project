package com.example.jacobcovey.game_board;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacobcovey.ticket_to_ride.R;

/**
 * Created by Riley on 5/31/2017.
 */

public class FragmentGameBoard extends Fragment{

    public static FragmentGameBoard newInstance() {
        return new FragmentGameBoard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_board, container, false);
        return v;
    }

}
