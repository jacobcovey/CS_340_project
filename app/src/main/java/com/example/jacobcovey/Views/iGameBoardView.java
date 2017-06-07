package com.example.jacobcovey.Views;

import com.example.jacobcovey.game_board.Route;

import java.util.List;

/**
 * Created by jacobcovey on 5/19/17.
 */

public interface iGameBoardView {
    void updateRoutes(List<Route> routes);

    public void closeTrainCardDrawer();

    public void closeDestinationCardDrawer();

    public void closeGameInfoDrawer();

    public void closeChatDrawer();

    public void closeHistoryDrawer();

    public void presentTrainCardDrawer();

    public void presentDestinationCardDrawer();

    public void presentGameInfoDrawer();

    public void presentGameOverDrawer();

    public void presentChatDrawer();

    public void presentHistoryDrawer();

    public void setDrawTrainButtonText(String text);
    public void setDrawDestinationButtonText(String text);
    public void setClaimRouteButtonText(String text);

    public void setDrawTrainButtonEnable(boolean enable);
    public void setDrawDestinationButtonEnable(boolean enable);
    public void setClaimRouteButtonEnable(boolean enable);

    void closeDrawers();

    public void displayToast(String message);
}
