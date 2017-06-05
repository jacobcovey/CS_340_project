package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.iGameBoardPresenter;

import static com.example.jacobcovey.constants.Constants.ONE_TRAIN_CARD_SELECTED;

/**
 * Created by billrichards on 6/5/17.
 */

public class OneTrainCardSelectedTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;
    private final String name = ONE_TRAIN_CARD_SELECTED;

    public OneTrainCardSelectedTurn(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        setButtons();
    }

    private void setButtons() {
        gameBoardPresenter.setDrawTrainButton("Draw Train Card", true);
        gameBoardPresenter.setDrawDestinationButton("Draw Destinations", false);
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
