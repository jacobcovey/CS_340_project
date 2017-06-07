package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.iGameBoardPresenter;

import static com.example.jacobcovey.constants.Constants.CLAIMING_ROUTE;

/**
 * Created by billrichards on 6/6/17.
 */

public class ClaimingRoute implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;
    private final String name = CLAIMING_ROUTE;

    public ClaimingRoute(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        setButtons();
    }

    private void setButtons() {
        gameBoardPresenter.setDrawTrainButton("Draw Train Cards", false);
        gameBoardPresenter.setDrawDestinationButton("Draw Destinations", false);
        gameBoardPresenter.setClaimRouteButton("Cancel", true);
    }
    @Override
    public void drawDestinationCardsButtonPressed() {
        return;
    }

    @Override
    public void drawTrainCardsButtonPressed() {
        return;
    }

    @Override
    public void claimRouteButtonPressed() {
        gameBoardPresenter.setState(new YourTurn(gameBoardPresenter));
    }

    @Override
    public String getStateName() {
        return name;
    }
}
