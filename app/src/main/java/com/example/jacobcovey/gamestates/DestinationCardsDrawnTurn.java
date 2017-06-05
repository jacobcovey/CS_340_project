package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.iGameBoardPresenter;
import com.example.jacobcovey.gamestates.iGameBoardState;

/**
 * Created by billrichards on 6/3/17.
 */

public class DestinationCardsDrawnTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;

    public DestinationCardsDrawnTurn(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        setButtons();
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
}
