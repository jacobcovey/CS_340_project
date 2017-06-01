package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iDestinationPickerView;

import java.util.Observer;

/**
 * Created by billrichards on 5/30/17.
 */

public interface iDestinationPickerPresenter extends Observer {

    public void setDestinationView(iDestinationPickerView trainCardDrawerView);

    public void onChecked(int index, boolean checked);

    public void selectDestinations();

    public void setViewCreated(boolean created);

}
