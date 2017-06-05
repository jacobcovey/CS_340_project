package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.GameBoardPresenter;
import com.example.jacobcovey.Presenters.iGameBoardPresenter;

/**
 * Created by billrichards on 6/5/17.
 */

public class NotYourTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;

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
}
