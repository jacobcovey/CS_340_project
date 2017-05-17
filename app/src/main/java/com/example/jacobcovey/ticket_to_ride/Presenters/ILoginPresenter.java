package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.ILoginView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface ILoginPresenter {

    void login();

    void register();

    void setLoginView(ILoginView loginView);
}
