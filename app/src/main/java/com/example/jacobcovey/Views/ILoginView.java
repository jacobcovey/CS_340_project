package com.example.jacobcovey.Views;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface ILoginView {

    String getUserName();

    void setUserName(String username);

    String getPassword();

    void setPassword(String password);

    void navToGameListScreenActivity();

}
