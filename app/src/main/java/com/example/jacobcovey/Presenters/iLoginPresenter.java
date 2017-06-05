package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iLoginView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface iLoginPresenter {

    void login();

    void register();

    void setLoginView(iLoginView loginView);
}
