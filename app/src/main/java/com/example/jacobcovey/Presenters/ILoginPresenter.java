package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.ILoginView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface ILoginPresenter {

    void login();

    void register();

    void setLoginView(ILoginView loginView);
}
