package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.ILoginView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import shared.classes.User;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;

    private ClientPresenterFacade cpf;

    public LoginPresenter() {
        cpf = new ClientPresenterFacade();
    }

    @Override
    public void setLoginView(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login() {

        String username = loginView.getUserName();
        String password = loginView.getPassword();

        User user = new User(username,password);

        cpf.login(user);

        loginView.navToGameListScreenActivity();
    }

    @Override
    public void register() {
        String username = loginView.getUserName();
        String password = loginView.getPassword();

        User user = new User(username,password);

        cpf.register(user);

        loginView.navToGameListScreenActivity();
    }
}
