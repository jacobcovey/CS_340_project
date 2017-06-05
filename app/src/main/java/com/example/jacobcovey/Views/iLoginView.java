package com.example.jacobcovey.Views;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface iLoginView {

    String getUserName();

    void setUserName(String username);

    String getPassword();

    void setPassword(String password);

    void displayToast(String message);

    void navToGameListScreenActivity();

}
