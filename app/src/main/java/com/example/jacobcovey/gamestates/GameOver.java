package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.iGameBoardPresenter;

import static com.example.jacobcovey.constants.Constants.DESTINATION_CARDS_DRAWN;
import static com.example.jacobcovey.constants.Constants.GAME_OVER;

/**
 * Created by Dylan on 6/9/2017.
 */

public class GameOver implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;
    private final String name = GAME_OVER;

    public GameOver(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        gameBoardPresenter.presentGameOverDrawer();
        setButtons();
    }

    private void setButtons() {
        gameBoardPresenter.setDrawTrainButton("View Game Results", true);
        gameBoardPresenter.setDrawDestinationButton("Draw Destinations", false);
        gameBoardPresenter.setClaimRouteButton("Claim a Route", false);
    }

    @Override
    public void drawDestinationCardsButtonPressed() {
        gameBoardPresenter.presentGameOverDrawer();
    }

    @Override
    public void drawTrainCardsButtonPressed() {

    }

    @Override
    public void claimRouteButtonPressed() {

    }

    @Override
    public String getStateName() {
        return name;
    }
}
