package com.example.jacobcovey.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jacobcovey.Views.ChatView;
import com.example.jacobcovey.Views.DestinationPickerView;
import com.example.jacobcovey.Views.GameBoardView;
import com.example.jacobcovey.Views.GameHistoryView;
import com.example.jacobcovey.Views.GameInfoView;
import com.example.jacobcovey.Views.GameOverView;
import com.example.jacobcovey.Views.TrainCardDrawerView;
import com.example.jacobcovey.constants.Icons;
import com.example.jacobcovey.ticket_to_ride.R;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardActivity extends SingleFragmentActivity implements
        TrainCardDrawerView.TrainCardDrawerContainer,
        DestinationPickerView.DestinationCardDrawerContainer,
        GameInfoView.GameInfoDrawerContainer,
        ChatView.ChatDrawerContainer,
        GameHistoryView.GameHistoryDrawerContainer,
        GameOverView.GameOverDrawerContainer {

    private GameBoardView gameBoard;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Iconify.with(new FontAwesomeModule());
        new Icons(this);
        getMenuInflater().inflate(R.menu.gameboardmenu, menu);
        menu.findItem(R.id.action_chat).setIcon(Icons.chatIcon);
        menu.findItem(R.id.action_history).setIcon(Icons.historyIcon);
        menu.findItem(R.id.action_info).setIcon(Icons.gameInfoIcon);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.action_chat:
                gameBoard.presentChatDrawer();
                break;
            case R.id.action_history:
                gameBoard.presentHistoryDrawer();
                break;
            case R.id.action_info:
                gameBoard.presentGameInfoDrawer();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    protected Fragment createFragment() {
        gameBoard = new GameBoardView();
        return gameBoard;
    }

    @Override
    public void closeTrainCardDrawer() {
        gameBoard.closeTrainCardDrawer();
    }

    @Override
    public void closeGameInfoDrawer() {
        gameBoard.closeGameInfoDrawer();
    }

    @Override
    public void closeChatDrawer() {
        gameBoard.closeChatDrawer();
    }

    @Override
    public void closeGameHistoryDrawer() {
        gameBoard.closeHistoryDrawer();
    }

    @Override
    public void closeDestinationCardDrawer() {
        gameBoard.closeDestinationCardDrawer();
    }

    @Override
    public void closeGameOverDrawer() {
        gameBoard.closeGameInfoDrawer();
    }
}
