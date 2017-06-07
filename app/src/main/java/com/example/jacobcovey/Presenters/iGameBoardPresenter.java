package com.example.jacobcovey.Presenters;

import android.view.MotionEvent;
import android.view.View;

import com.example.jacobcovey.Views.iGameBoardView;
import com.example.jacobcovey.game_board.Route;
import com.example.jacobcovey.gamestates.iGameBoardState;

import java.util.List;
import java.util.Observer;

/**
 * Created by Riley on 5/31/2017.
 */

public interface iGameBoardPresenter extends Observer{
    public void setGameBoardView(iGameBoardView gameBoardView);

    public void setViewCreated(boolean viewCreated);

    public void updateBoard();

    public void updateClientRoot(List<Route> routes);

    public boolean setDrawTrainButton(String text, boolean enable);

    public boolean setDrawDestinationButton(String text, boolean enable);

    public boolean setClaimRouteButton(String text, boolean enable);

    public void setState(iGameBoardState state);

    public void drawDestinationCardsButtonPressed();

    public void drawTrainCardsButtonPressed();

    public void claimRouteButtonPressed();

    public void closeTrainCardDrawer();

    public void closeDestinationCardDrawer();

    public void closeGameInfoDrawer();

    public void closeChatDrawer();

    public void closeHistoryDrawer();

    public void presentTrainCardDrawer();

    public void presentDestinationCardDrawer();

    public void presentGameInfoDrawer();

    public void presentChatDrawer();

    public void presentHistoryDrawer();

    public boolean onMapTouch(View view, MotionEvent event);
}
