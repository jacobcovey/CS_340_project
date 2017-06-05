package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.GameBoardPresenter;
import com.example.jacobcovey.Presenters.iGameBoardPresenter;

import static com.example.jacobcovey.constants.Constants.NOT_YOUR_TURN;

/**
 * Created by billrichards on 6/5/17.
 */

public class NotYourTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;
    private final String name = NOT_YOUR_TURN;

    public NotYourTurn(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        setButtons();
    }

    private void setButtons() {
        gameBoardPresenter.setDrawTrainButton("View Train Cards", true);
        gameBoardPresenter.setDrawDestinationButton("Draw Destinations", true);
        gameBoardPresenter.setClaimRouteButton("Claim a Route", false);
    }

    @Override
    public void drawDestinationCardsButtonPressed() {
        return;
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
