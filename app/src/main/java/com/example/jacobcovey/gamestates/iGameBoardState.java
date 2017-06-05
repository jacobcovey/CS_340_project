package com.example.jacobcovey.gamestates;

import android.widget.Button;

/**
 * Created by billrichards on 6/3/17.
 */

public interface iGameBoardState {
    public void drawDestinationCardsButtonPressed();
    public void drawTrainCardsButtonPressed();
    public void claimRouteButtonPressed();

    public String getStateName();
}
