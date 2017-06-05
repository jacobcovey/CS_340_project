package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.iGameBoardPresenter;

/**
 * Created by billrichards on 6/5/17.
 */

public class OneTrainCardSelectedTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;

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
}
