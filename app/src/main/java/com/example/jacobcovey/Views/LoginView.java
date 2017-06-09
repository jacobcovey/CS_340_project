package com.example.jacobcovey.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacobcovey.Activities.GameListActivity;
import com.example.jacobcovey.Presenters.iLoginPresenter;
import com.example.jacobcovey.Presenters.LoginPresenter;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.ticket_to_ride.R;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class LoginView extends android.support.v4.app.Fragment implements iLoginView {

    private EditText mUserNameTextEdit;
    private EditText mPasswordTextEdit;
    private EditText mIpTextEdit;
    private EditText mPortTextEdit;
    private Button mLoginButton;
    private Button mRegisterButton;

    private iLoginPresenter loginPresenter = new LoginPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter();
        loginPresenter.setLoginView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        mUserNameTextEdit = (EditText) v.findViewById(R.id.login_username_text_edit);

        mPasswordTextEdit = (EditText) v.findViewById(R.id.login_password_text_edit);

        mIpTextEdit = (EditText) v.findViewById(R.id.login_ip_address_text_edit);

        mPortTextEdit = (EditText) v.findViewById(R.id.login_port_text_edit);

        mLoginButton = (Button) v.findViewById(R.id.login_login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
                setIPandPort();
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.login_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.register();
                setIPandPort();
            }
        });

        return v;
    }

    public void setIPandPort() {
        ClientModelRoot._instance.setIPaddress(mIpTextEdit.getText().toString());
        ClientModelRoot._instance.setPortNumber(mPortTextEdit.getText().toString());
    }

    @Override
    public void displayToast(final String message) {

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getActivity(), message,
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void navToGameListScreenActivity() {
        Intent intent = new Intent(getActivity(), GameListActivity.class);
        startActivity(intent);
    }

    @Override
    public String getUserName() {
        return mUserNameTextEdit.getText().toString();
    }

    @Override
    public void setUserName(String username) {
        mUserNameTextEdit.setText(username);
    }

    @Override
    public String getPassword() {
        return mPasswordTextEdit.getText().toString();
    }

    @Override
    public void setPassword(String password) {
        mPasswordTextEdit.setText(password);
    }
}
