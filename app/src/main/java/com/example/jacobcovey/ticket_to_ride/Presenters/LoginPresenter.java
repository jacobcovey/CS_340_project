package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.ILoginView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;


    @Override
    public void setLoginView(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login() {

        String username = loginView.getUserName();
        String password = loginView.getPassword();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

    }

    @Override
    public void register() {
        String username = loginView.getUserName();
        String password = loginView.getPassword();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

    }
}
