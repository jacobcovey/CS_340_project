package com.example.jacobcovey.Activities;

import android.os.Bundle;

import com.example.jacobcovey.Views.LoginView;
import com.example.jacobcovey.constants.Icons;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected android.support.v4.app.Fragment createFragment() {
        return new LoginView();
    }

}
