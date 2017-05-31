package com.example.jacobcovey.Views;

import shared.classes.DestinationCard;

/**
 * Created by billrichards on 5/30/17.
 */

public interface iDestinationPickerView {

    public void enableSelectButton(boolean enable);

    public int getNumberChecker();

    public void setMessage(String message);

    public void setDestination0(DestinationCard destinationCard);

    public void setDestination1(DestinationCard destinationCard);

    public void setDestination2(DestinationCard destinationCard);

    public DestinationCard getDestination0();

    public DestinationCard getDestination1();

    public DestinationCard getDestination2();

    public void displayToast(String message);

    public void closeDestinationDrawer();

}
