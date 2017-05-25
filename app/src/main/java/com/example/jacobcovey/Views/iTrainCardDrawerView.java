package com.example.jacobcovey.Views;

import shared.classes.TrainCard;

/**
 * Created by billrichards on 5/24/17.
 */

public interface iTrainCardDrawerView {

    public TrainCard getCard0();

    public TrainCard getCard1();

    public TrainCard getCard2();

    public TrainCard getCard3();

    public TrainCard getCard4();

    public void setCard0(TrainCard card);

    public void setCard1(TrainCard card);

    public void setCard2(TrainCard card);

    public void setCard3(TrainCard card);

    public void setCard4(TrainCard card);

    public void displayToast(String message);

}
