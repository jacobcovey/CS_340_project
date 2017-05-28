package com.example.jacobcovey.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacobcovey.Activities.GameBoardActivity;
import com.example.jacobcovey.Activities.GameListActivity;
import com.example.jacobcovey.Presenters.GameLobbyPresenter;
import com.example.jacobcovey.Presenters.IGameLobbyPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameLobbyView extends Fragment implements IGameLobbyView {

    public static final int MAXNUMBEROFPLAYERS = 5;

    private Button mLeaveGameButton;

    private TextView mGameName;
    private TextView mGameCreatorName;

    private LinearLayout mPlayerOneBox;
    private LinearLayout mPlayerTwoBox;
    private LinearLayout mPlayerThreeBox;
    private LinearLayout mPlayerFourBox;
    private LinearLayout mPlayerFiveBox;

    private Stack<LinearLayout> playerBoxStack;

    private TextView mPlayerNameOne;
    private TextView mPlayerNameTwo;
    private TextView mPlayerNameThree;
    private TextView mPlayerNameFour;
    private TextView mPlayerNameFive;

    private List<TextView> playerNameTextViewList;
    private List<String> playerNameList;

    private IGameLobbyPresenter gameLobbyPresenter;

    private Boolean unusedhidden = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameLobbyPresenter = new GameLobbyPresenter();
        gameLobbyPresenter.setGameLobbyView(this);

        if (getActivity().getIntent().hasExtra("gameId")) {
            gameLobbyPresenter.setToCurrentState();
            String gameId = (String) getActivity().getIntent().getExtras().get("gameId");
            gameLobbyPresenter.setCurrentGame(gameId);
            gameLobbyPresenter.joinCurrentGame();
        }

        playerBoxStack = new Stack<LinearLayout>();
        playerNameTextViewList = new ArrayList<TextView>();
        playerNameList = new ArrayList<String>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.game_lobby_fragment, container, false);

        mGameName = (TextView) v.findViewById(R.id.lobby_game_name_textView);
        mGameCreatorName = (TextView) v.findViewById(R.id.lobby_created_by_textView);

        mPlayerOneBox = (LinearLayout) v.findViewById(R.id.lobby_player_one_box);
        mPlayerTwoBox = (LinearLayout) v.findViewById(R.id.lobby_player_two_box);
        mPlayerThreeBox = (LinearLayout) v.findViewById(R.id.lobby_player_three_box);
        mPlayerFourBox = (LinearLayout) v.findViewById(R.id.lobby_player_four_box);
        mPlayerFiveBox = (LinearLayout) v.findViewById(R.id.lobby_player_five_box);

        playerBoxStack.push(mPlayerOneBox);
        playerBoxStack.push(mPlayerTwoBox);
        playerBoxStack.push(mPlayerThreeBox);
        playerBoxStack.push(mPlayerFourBox);
        playerBoxStack.push(mPlayerFiveBox);

        mPlayerNameOne = (TextView) v.findViewById(R.id.lobby_player_one_textView);
        mPlayerNameTwo = (TextView) v.findViewById(R.id.lobby_player_two_textView);
        mPlayerNameThree = (TextView) v.findViewById(R.id.lobby_player_three_textView);
        mPlayerNameFour = (TextView) v.findViewById(R.id.lobby_player_four_textView);
        mPlayerNameFive = (TextView) v.findViewById(R.id.lobby_player_five_textView);

        playerNameTextViewList.add(mPlayerNameOne);
        playerNameTextViewList.add(mPlayerNameTwo);
        playerNameTextViewList.add(mPlayerNameThree);
        playerNameTextViewList.add(mPlayerNameFour);
        playerNameTextViewList.add(mPlayerNameFive);

        mLeaveGameButton = (Button) v.findViewById(R.id.lobby_leave_game_button);
        mLeaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameLobbyPresenter.leaveGame();
            }
        });

        gameLobbyPresenter.setViewCreated(true);
        return v;
    }

    @Override
    public void setPlayerList(List<String> names) {
        playerNameList = new ArrayList<String>();
        for (String name: names) {
            playerNameList.add(name);
        }
        writePlayerNames();
    }

    @Override
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

    @Override
    public void navToGameListScreenActivity() {
        Intent intent = new Intent(getActivity(), GameListActivity.class);
        startActivity(intent);
    }

    @Override
    public void navToGameBoardScreenActivity() {
        Intent intent = new Intent(getActivity(), GameBoardActivity.class);
        startActivity(intent);
    }

    @Override
    public void setGameName(final String gameName) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                mGameName.setText(gameName);
            }
        });
    }

    @Override
    public void setGameCreator(final String gameCreator) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                mGameCreatorName.setText("Created by: " + gameCreator);
            }
        });
    }

    public void writePlayerNames() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (int x = 0; x < playerNameTextViewList.size(); x++ ) {
                    TextView textView = playerNameTextViewList.get(x);
                    if (playerNameList.size() > x) {
                        textView.setText(playerNameList.get(x));
                    } else {
                        textView.setText("Waiting for Player...");
                    }
                }
            }
        });
    }
}
