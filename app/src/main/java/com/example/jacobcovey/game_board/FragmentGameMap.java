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

public class FragmentGameMap extends Fragment{

    public static FragmentGameMap newInstance() {
        return new FragmentGameMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_map, container, false);
        return v;
    }

}
