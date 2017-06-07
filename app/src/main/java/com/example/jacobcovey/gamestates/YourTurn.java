package com.example.jacobcovey.gamestates;

import com.example.jacobcovey.Presenters.GameBoardPresenter;
import com.example.jacobcovey.Presenters.iGameBoardPresenter;

import static com.example.jacobcovey.constants.Constants.YOUR_TURN;

/**
 * Created by billrichards on 6/5/17.
 */

public class YourTurn implements iGameBoardState {
    private iGameBoardPresenter gameBoardPresenter;
    private final String name = YOUR_TURN;

    public YourTurn(iGameBoardPresenter gameBoardPresenter) {
        this.gameBoardPresenter = gameBoardPresenter;
        setButtons();
    }

    private void setButtons() {
        gameBoardPresenter.setDrawTrainButton("Draw Train Cards", true);
        gameBoardPresenter.setDrawDestinationButton("Draw Destinations", true);
        gameBoardPresenter.setClaimRouteButton("Claim a Route", true);
    }

    @Override
    public void drawDestinationCardsButtonPressed() {
        gameBoardPresenter.presentDestinationCardDrawer();
        gameBoardPresenter.setState(new DestinationCardsDrawnTurn(gameBoardPresenter));
    }

    @Override
    public void drawTrainCardsButtonPressed() {
        gameBoardPresenter.presentTrainCardDrawer();
    }

    @Override
    public void claimRouteButtonPressed() {
        gameBoardPresenter.setState(new ClaimingRoute(gameBoardPresenter));
    }

    @Override
    public String getStateName() {
        return name;
    }
}
