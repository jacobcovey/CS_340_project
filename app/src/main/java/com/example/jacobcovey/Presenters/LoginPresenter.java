package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.ILoginView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;

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

        try {
            cpf.login(user);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        loginView.navToGameListScreenActivity();
    }

    @Override
    public void register() {
        String username = loginView.getUserName();
        String password = loginView.getPassword();

        User user = new User(username,password);

        try {
            cpf.register(user);
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
        loginView.navToGameListScreenActivity();
    }
}
