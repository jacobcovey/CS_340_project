package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.iGameBoardPresenter;

import static com.example.jacobcovey.constants.Constants.FIRST_TURN;

/**
 * Created by billrichards on 6/3/17.
 */

public class YourFirstTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;
    private final String name = FIRST_TURN;
    
    public YourFirstTurn(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        setButtons();
        gameBoardPresenter.presentDestinationCardDrawer();
    }

    private void setButtons() {
        gameBoardPresenter.setDrawTrainButton("View Train Cards", true);
        gameBoardPresenter.setDrawDestinationButton("Select Destinations", true);
        gameBoardPresenter.setClaimRouteButton("Claim a Route", false);
    }

    @Override
    public void drawDestinationCardsButtonPressed() {
        gameBoardPresenter.presentDestinationCardDrawer();
    }

    @Override
    public void drawTrainCardsButtonPressed() {
        gameBoardPresenter.presentTrainCardDrawer();
    }

    @Override
    public void claimRouteButtonPressed() {
        return;
    }

    @Override
    public String getStateName() {
        return name;
    }
}
