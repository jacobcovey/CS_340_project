package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iTrainCardDrawerView;

import java.util.Observer;

/**
 * Created by billrichards on 5/24/17.
 */

public interface iTrainCardDrawerPresenter extends Observer {

    public void setTrainCardDrawerView(iTrainCardDrawerView trainCardDrawerView);

    public void setViewCreated(Boolean viewCreated);

    public void pickFaceUpCard(int index);

    public void drawFaceDownCard();

    public void removeObserver();

    public void setFaceUpDeck();

}
