package com.example.jacobcovey.Views;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.jacobcovey.ticket_to_ride.R;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardFragment extends android.support.v4.app.Fragment implements IGameBoardView {

    private DrawerLayout drawerLayout;
    private Button leftDrawerButton, rightDrawerButton;
    private FrameLayout leftDrawer, rightDrawer;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_board_fragment, container, false);
        fragmentManager =  ((Activity) v.getContext()).getFragmentManager();
        drawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        leftDrawer = (FrameLayout) v.findViewById(R.id.left_drawer);
        rightDrawer = (FrameLayout) v.findViewById(R.id.right_drawer);
        leftDrawerButton = (Button) v.findViewById(R.id.leftDrawerButton);
        rightDrawerButton = (Button) v.findViewById(R.id.rightDrawerButton);

        leftDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLeftDrawer(new TrainCardDrawerView());
            }
        });

        rightDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRightDrawer(null);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
        });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        return v;
    }


    public void closeTrainCardDrawer() {
        dismissLeftDrawer();
    }

    private void showLeftDrawer(Fragment fragment) {

        if (!drawerLayout.isDrawerOpen(leftDrawer)) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.left_drawer, fragment);
            fragmentTransaction.commit();
            drawerLayout.requestLayout();
            drawerLayout.openDrawer(leftDrawer);
        }

    }

    private void showRightDrawer(Fragment fragment) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.right_drawer, fragment);
        fragmentTransaction.commit();
        drawerLayout.openDrawer(rightDrawer);

    }

    private void dismissLeftDrawer() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.left_drawer));
        fragmentTransaction.commit();
        drawerLayout.closeDrawer(leftDrawer);

    }

    private void dismissRightDrawer() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.right_drawer));
        fragmentTransaction.commit();
        drawerLayout.closeDrawer(rightDrawer);

    }
}
