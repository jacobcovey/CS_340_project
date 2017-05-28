package com.example.jacobcovey.Activities;

import com.example.jacobcovey.Views.LoginView;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected android.support.v4.app.Fragment createFragment() {
        return new LoginView();
    }

}
