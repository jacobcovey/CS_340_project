package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

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

        loginRequest login = new loginRequest();
        login.execute(user);
    }

    @Override
    public void register() {
        String username = loginView.getUserName();
        String password = loginView.getPassword();

        User user = new User(username,password);

        registerRequest regester = new registerRequest();
        regester.execute(user);
    }

    private class loginRequest extends AsyncTask<User, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(User... params) {
            try {
                cpf.login(params[0]);
            } catch (IOException e) {
                System.out.printf(e.getMessage());
                loginView.displayToast("login failed");
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                loginView.navToGameListScreenActivity();
            }
        }
    }

    private class registerRequest extends AsyncTask<User, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(User... params) {
            try {
                cpf.register(params[0]);
            } catch (IOException e) {
                System.out.printf(e.getMessage());
                loginView.displayToast("Register failed");
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                loginView.navToGameListScreenActivity();
            }
        }
    }
}
