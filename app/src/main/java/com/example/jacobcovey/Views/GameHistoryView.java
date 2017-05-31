package com.example.jacobcovey.Views;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jacobcovey.Presenters.GameHistoryPresenter;
import com.example.jacobcovey.Presenters.IGameHistoryPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import shared.classes.History;
import shared.classes.HistoryAction;

/**
 * Created by spencer on 5/25/17.
 */

public class GameHistoryView extends Fragment implements IGameHistoryView {


    private Button dissmissButton;
    private RecyclerView historyRecyclerView;
    private IGameHistoryPresenter gameHistoryPresenter;
    private GameHistoryAdapter adapter;
    private LinearLayoutManager manager;

    public interface GameHistoryDrawerContainer {
        public void closeGameHistoryDrawer();
    }

    private GameHistoryDrawerContainer gameHistoryDrawerContainer;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {

            gameHistoryDrawerContainer = (GameHistoryDrawerContainer) activity;

        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement GameHistoryDrawerContainer");

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameHistoryPresenter = new GameHistoryPresenter();
        gameHistoryPresenter.setGameHistoryView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.history_fragment, container, false);

        dissmissButton = (Button) v.findViewById(R.id.history_dismiss_button);
        dissmissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameHistoryDrawerContainer.closeGameHistoryDrawer();
            }
        });

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        historyRecyclerView = (RecyclerView) v.findViewById(R.id.history_recycler_view);
        History gameHistory = gameHistoryPresenter.getGameHistory();
        adapter = new GameHistoryAdapter(gameHistory);

        historyRecyclerView.setAdapter(adapter);
        historyRecyclerView.setLayoutManager(manager);


        return v;

    }

    @Override
    public void addHistoryEvent(HistoryAction action) {

    }


}
