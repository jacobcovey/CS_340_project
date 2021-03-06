package com.example.jacobcovey.Views;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PointF;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.jacobcovey.game_board.Route;
import com.example.jacobcovey.game_board.TouchHandler;
import com.example.jacobcovey.game_board.ViewGameMap;
import com.example.jacobcovey.Presenters.iGameBoardPresenter;
import com.example.jacobcovey.Presenters.GameBoardPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardView extends android.support.v4.app.Fragment implements iGameBoardView {

    private DrawerLayout drawerLayout;
    private Button trainCardDrawerButton, destinationCardDrawerButton, claimRouteButton;
    private FrameLayout leftDrawer, rightDrawer;
    private FragmentManager fragmentManager;
    private ViewGameMap mMap;
    private iGameBoardPresenter gameBoardPresenter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameBoardPresenter = new GameBoardPresenter();
        gameBoardPresenter.setGameBoardView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_board_fragment, container, false);
        fragmentManager =  ((Activity) v.getContext()).getFragmentManager();
        drawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        leftDrawer = (FrameLayout) v.findViewById(R.id.left_drawer);
        rightDrawer = (FrameLayout) v.findViewById(R.id.right_drawer);
        trainCardDrawerButton = (Button) v.findViewById(R.id.trainCardDrawerButton);

        trainCardDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameBoardPresenter.drawTrainCardsButtonPressed();
            }
        });

        destinationCardDrawerButton = (Button) v.findViewById(R.id.destinationCardDrawerButton);

        destinationCardDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameBoardPresenter.drawDestinationCardsButtonPressed();
            }
        });

        claimRouteButton = (Button) v.findViewById(R.id.claimRouteButton);

        claimRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoardPresenter.claimRouteButtonPressed();
                mMap.setClickable(true);
            }
        });

        mMap = (ViewGameMap) v.findViewById(R.id.board_view);

        mMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return gameBoardPresenter.onMapTouch(view, event);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

        });
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        gameBoardPresenter.setViewCreated(true);
        return v;
    }

    @Override
    public void closeTrainCardDrawer() {
        dismissLeftDrawer();
    }

    @Override
    public void closeDestinationCardDrawer() {
        dismissLeftDrawer();
    }

    @Override
    public void closeGameInfoDrawer() {
        dismissRightDrawer();
    }

    @Override
    public void closeChatDrawer() {
        dismissRightDrawer();
    }

    @Override
    public void closeHistoryDrawer() {
        dismissRightDrawer();
    }

    @Override
    public void presentTrainCardDrawer() {
        showLeftDrawer(new TrainCardDrawerView());
    }

    @Override
    public void presentDestinationCardDrawer() {
        showLeftDrawer(new DestinationPickerView());
    }

    @Override
    public void presentGameInfoDrawer() {
        showRightDrawer(new GameInfoView());
    }

    @Override
    public void presentGameOverDrawer() {
        showRightDrawer(new GameOverView());
    }

    @Override
    public void presentChatDrawer() {
        showRightDrawer(new ChatView());
    }

    @Override
    public void presentHistoryDrawer() {
        showRightDrawer(new GameHistoryView());
    }

    @Override
    public void setDrawTrainButtonText(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                trainCardDrawerButton.setText(text);
            }
        });
    }

    @Override
    public void setDrawDestinationButtonText(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                destinationCardDrawerButton.setText(text);
            }
        });
    }

    @Override
    public void setClaimRouteButtonText(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                claimRouteButton.setText(text);
            }
        });
    }

    @Override
    public void setDrawTrainButtonEnable(final boolean enable) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                trainCardDrawerButton.setEnabled(enable);
            }
        });
    }

    @Override
    public void setDrawDestinationButtonEnable(final boolean enable) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                destinationCardDrawerButton.setEnabled(enable);
            }
        });
    }

    @Override
    public void setClaimRouteButtonEnable(final boolean enable) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                claimRouteButton.setEnabled(enable);
            }
        });
    }

    @Override
    public void closeDrawers() {
        dismissLeftDrawer();
        dismissRightDrawer();
    }


    private void showLeftDrawer(final Fragment fragment) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!drawerLayout.isDrawerOpen(leftDrawer)) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.left_drawer, fragment);
                        fragmentTransaction.commit();
                        drawerLayout.requestLayout();
                        drawerLayout.openDrawer(leftDrawer);
                    }
                }
            });

    }

    private void showRightDrawer(final Fragment fragment) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!drawerLayout.isDrawerOpen(rightDrawer)) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.right_drawer, fragment);
                        fragmentTransaction.commit();
                        drawerLayout.requestLayout();
                        drawerLayout.openDrawer(rightDrawer);
                    }
                }
            });

    }

    private void dismissLeftDrawer() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (drawerLayout.isDrawerOpen(leftDrawer)) {
                        removeFragmentFromDrawer(R.id.left_drawer);
                        drawerLayout.closeDrawer(leftDrawer);
                    }
                }
            });

    }

    private void dismissRightDrawer() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (drawerLayout.isDrawerOpen(rightDrawer)) {
                        removeFragmentFromDrawer(R.id.right_drawer);
                        drawerLayout.closeDrawer(rightDrawer);
                    }
                }
            });

    }

    private void removeFragmentFromDrawer(final int drawerId) {

        if (fragmentManager.findFragmentById(drawerId) != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.remove(fragmentManager.findFragmentById(drawerId));
            fragmentTransaction.commit();
        }

    }

    @Override
    public void updateRoutes(final List<Route> routes) {
        if (routes != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                mMap.setRoutes(routes);
                mMap.invalidate();
                }
            });

        }
    }

    @Override
    public void displayToast(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
