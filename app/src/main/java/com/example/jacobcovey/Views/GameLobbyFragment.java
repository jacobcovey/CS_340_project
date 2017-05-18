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
import android.widget.Toast;

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

public class GameLobbyFragment extends Fragment implements IGameLobbyView {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity().getIntent().hasExtra("gameId")) {
            String gameId = (String) getActivity().getIntent().getExtras().get("gameId");
            gameLobbyPresenter.setCurrentGame(gameId);
        }

        playerBoxStack = new Stack<LinearLayout>();
        playerNameTextViewList = new ArrayList<TextView>();
        playerNameList = new ArrayList<String>();

        gameLobbyPresenter = new GameLobbyPresenter();
        gameLobbyPresenter.setGameLobbyView(this);
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
//        --------------Temporary
        gameLobbyPresenter.update();
        return v;
//        --------------------------
    }

    @Override
    public void setPlayerList(List<String> names) {
        for (String name: names) {
            playerNameList.add(name);
        }
        writePlayerNames();
    }

    @Override
    public void hideUnusedPlayers(int numGamePlayers) {
        int numToHide = MAXNUMBEROFPLAYERS - numGamePlayers;

        Stack<LinearLayout> tempStack = playerBoxStack;

        for (int x = 0; x < numToHide; x++) {
            LinearLayout box = tempStack.pop();
            box.setVisibility(View.GONE);
        }
    }

    @Override
    public void navToGameListScreenActivity() {
        Intent intent = new Intent(getActivity(), GameListActivity.class);
        startActivity(intent);
    }

    @Override
    public void navToGameBoardScreenActivity() {
        Toast.makeText(getActivity(), "Game will now begin",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void setGameName(String gameName) {
        mGameName.setText(gameName);
    }

    @Override
    public void setGameCreator(String gameCreator) {
        mGameCreatorName.setText(gameCreator);
    }

    public void writePlayerNames() {
        for (int x = 0; x < playerNameList.size(); x++ ) {
            TextView textView = playerNameTextViewList.get(x);
            textView.setText(playerNameList.get(x));
        }
    }
}
