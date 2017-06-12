package com.example.jacobcovey.Views;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacobcovey.Activities.LoginActivity;
import com.example.jacobcovey.Presenters.GameOverPresenter;
import com.example.jacobcovey.Presenters.iGameOverPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import shared.classes.PlayerPoints;

/**
 * Created by jacobcovey on 6/5/17.
 */

public class GameOverView extends Fragment implements iGameOverView {

    public static final int MAXNUMBEROFPLAYERS = 5;

    iGameOverPresenter gameOverPresenter;

    private Button exitButton;

    private Boolean unusedhidden = false;

    private TextView winningPlayer;

    private LinearLayout player1box;
    private LinearLayout player2box;
    private LinearLayout player3box;
    private LinearLayout player4box;
    private LinearLayout player5box;

    private TextView player1NameTextView;
    private TextView player2NameTextView;
    private TextView player3NameTextView;
    private TextView player4NameTextView;
    private TextView player5NameTextView;

    private TextView player1crpTextView;
    private TextView player2crpTextView;
    private TextView player3crpTextView;
    private TextView player4crpTextView;
    private TextView player5crpTextView;

    private TextView player1lrpTextView;
    private TextView player2lrpTextView;
    private TextView player3lrpTextView;
    private TextView player4lrpTextView;
    private TextView player5lrpTextView;

    private TextView player1drpTextView;
    private TextView player2drpTextView;
    private TextView player3drpTextView;
    private TextView player4drpTextView;
    private TextView player5drpTextView;

    private TextView player1udpTextView;
    private TextView player2udpTextView;
    private TextView player3udpTextView;
    private TextView player4udpTextView;
    private TextView player5udpTextView;

    private TextView player1tpTextView;
    private TextView player2tpTextView;
    private TextView player3tpTextView;
    private TextView player4tpTextView;
    private TextView player5tpTextView;

    private Stack<LinearLayout> playerBoxStack = new Stack<LinearLayout>();

    private List<TextView> playerNamesList = new ArrayList<TextView>();
    private List<TextView> playerCrpList = new ArrayList<TextView>();
    private List<TextView> playerLrpList = new ArrayList<TextView>();
    private List<TextView> playerDrpList = new ArrayList<TextView>();
    private List<TextView> playerUdpList = new ArrayList<TextView>();
    private List<TextView> playerTpList = new ArrayList<TextView>();

    public interface GameOverDrawerContainer {
        public void closeGameOverDrawer();
    }

    private GameOverDrawerContainer gameOverDrawerContainer;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {

            gameOverDrawerContainer = (GameOverDrawerContainer) activity;

        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement GameOverDrawerContainer");

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameOverPresenter = new GameOverPresenter();
        gameOverPresenter.setGameOverView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_over_fragment, container, false);

        winningPlayer = (TextView) v.findViewById(R.id.game_over_winning_player_text_view);

        exitButton = (Button) v.findViewById(R.id.game_over_exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                gameOverDrawerContainer.closeGameOverDrawer();
                gameOverPresenter.removeObserver();
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        player1box = (LinearLayout) v.findViewById(R.id.game_over_p1_box);
        player2box = (LinearLayout) v.findViewById(R.id.game_over_p2_box);
        player3box = (LinearLayout) v.findViewById(R.id.game_over_p3_box);
        player4box = (LinearLayout) v.findViewById(R.id.game_over_p4_box);
        player5box = (LinearLayout) v.findViewById(R.id.game_over_p5_box);

        playerBoxStack.push(player1box);
        playerBoxStack.push(player2box);
        playerBoxStack.push(player3box);
        playerBoxStack.push(player4box);
        playerBoxStack.push(player5box);

        player1NameTextView = (TextView) v.findViewById(R.id.game_over_p1_name);
        player2NameTextView = (TextView) v.findViewById(R.id.game_over_p2_name);
        player3NameTextView = (TextView) v.findViewById(R.id.game_over_p3_name);
        player4NameTextView = (TextView) v.findViewById(R.id.game_over_p4_name);
        player5NameTextView = (TextView) v.findViewById(R.id.game_over_p5_name);

        playerNamesList.add(player1NameTextView);
        playerNamesList.add(player2NameTextView);
        playerNamesList.add(player3NameTextView);
        playerNamesList.add(player4NameTextView);
        playerNamesList.add(player5NameTextView);

        player1crpTextView = (TextView) v.findViewById(R.id.game_over_p1_crp);
        player2crpTextView = (TextView) v.findViewById(R.id.game_over_p2_crp);
        player3crpTextView = (TextView) v.findViewById(R.id.game_over_p3_crp);
        player4crpTextView = (TextView) v.findViewById(R.id.game_over_p4_crp);
        player5crpTextView = (TextView) v.findViewById(R.id.game_over_p5_crp);

        playerCrpList.add(player1crpTextView);
        playerCrpList.add(player2crpTextView);
        playerCrpList.add(player3crpTextView);
        playerCrpList.add(player4crpTextView);
        playerCrpList.add(player5crpTextView);

        player1lrpTextView = (TextView) v.findViewById(R.id.game_over_p1_lrp);
        player2lrpTextView = (TextView) v.findViewById(R.id.game_over_p2_lrp);
        player3lrpTextView = (TextView) v.findViewById(R.id.game_over_p3_lrp);
        player4lrpTextView = (TextView) v.findViewById(R.id.game_over_p4_lrp);
        player5lrpTextView = (TextView) v.findViewById(R.id.game_over_p5_lrp);

        playerLrpList.add(player1lrpTextView);
        playerLrpList.add(player2lrpTextView);
        playerLrpList.add(player3lrpTextView);
        playerLrpList.add(player4lrpTextView);
        playerLrpList.add(player5lrpTextView);

        player1drpTextView = (TextView) v.findViewById(R.id.game_over_p1_drp);
        player2drpTextView = (TextView) v.findViewById(R.id.game_over_p2_drp);
        player3drpTextView = (TextView) v.findViewById(R.id.game_over_p3_drp);
        player4drpTextView = (TextView) v.findViewById(R.id.game_over_p4_drp);
        player5drpTextView = (TextView) v.findViewById(R.id.game_over_p5_drp);

        playerDrpList.add(player1drpTextView);
        playerDrpList.add(player2drpTextView);
        playerDrpList.add(player3drpTextView);
        playerDrpList.add(player4drpTextView);
        playerDrpList.add(player5drpTextView);

        player1udpTextView = (TextView) v.findViewById(R.id.game_over_p1_udp);
        player2udpTextView = (TextView) v.findViewById(R.id.game_over_p2_udp);
        player3udpTextView = (TextView) v.findViewById(R.id.game_over_p3_udp);
        player4udpTextView = (TextView) v.findViewById(R.id.game_over_p4_udp);
        player5udpTextView = (TextView) v.findViewById(R.id.game_over_p5_udp);

        playerUdpList.add(player1udpTextView);
        playerUdpList.add(player2udpTextView);
        playerUdpList.add(player3udpTextView);
        playerUdpList.add(player4udpTextView);
        playerUdpList.add(player5udpTextView);

        player1tpTextView = (TextView) v.findViewById(R.id.game_over_p1_tp);
        player2tpTextView = (TextView) v.findViewById(R.id.game_over_p2_tp);
        player3tpTextView = (TextView) v.findViewById(R.id.game_over_p3_tp);
        player4tpTextView = (TextView) v.findViewById(R.id.game_over_p4_tp);
        player5tpTextView = (TextView) v.findViewById(R.id.game_over_p5_tp);

        playerTpList.add(player1tpTextView);
        playerTpList.add(player2tpTextView);
        playerTpList.add(player3tpTextView);
        playerTpList.add(player4tpTextView);
        playerTpList.add(player5tpTextView);

        setScores(gameOverPresenter.getPlayersPoints());
        setWinner(gameOverPresenter.getWinningPlayerName());

        return v;
    }

    @Override
    public void setScores(final List<PlayerPoints> playerPointsList) {

        hideUnusedPlayers(playerPointsList.size());

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (int x = 0; x < playerPointsList.size(); x++) {
                    playerNamesList.get(x).setText(playerPointsList.get(x).getUserName());
                    playerCrpList.get(x).setText((Integer.toString(playerPointsList.get(x).getClaimedRoutPoints())));
                    playerLrpList.get(x).setText(Integer.toString(playerPointsList.get(x).getLongestRoutePoints()));
                    playerDrpList.get(x).setText(Integer.toString(playerPointsList.get(x).getDestinationsReachedPoints()));
                    playerUdpList.get(x).setText(Integer.toString(playerPointsList.get(x).getUnreachedDestinationPoints()));
                    playerTpList.get(x).setText(Integer.toString(playerPointsList.get(x).getTotalPoints()));
                }

            }
        });

    }

    @Override
    public void setWinner(final String playerName) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                winningPlayer.setText(playerName + " Wins");
            }
        });
    }

    public void hideUnusedPlayers(final int numGamePlayers) {
        if (unusedhidden == false) {
            unusedhidden = true;

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    int numToHide = MAXNUMBEROFPLAYERS - numGamePlayers;

                    Stack<LinearLayout> tempStack = playerBoxStack;

                    for (int x = 0; x < numToHide; x++) {
                        LinearLayout box = tempStack.pop();
                        box.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

}
