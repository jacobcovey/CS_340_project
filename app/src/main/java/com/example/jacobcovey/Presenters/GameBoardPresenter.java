package com.example.jacobcovey.Presenters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.example.jacobcovey.Views.iGameBoardView;
import com.example.jacobcovey.game_board.Route;

import com.example.jacobcovey.game_board.TouchHandler;
import com.example.jacobcovey.gamestates.DestinationCardsDrawnTurn;
import com.example.jacobcovey.gamestates.NotYourTurn;
import com.example.jacobcovey.gamestates.OneTrainCardSelectedTurn;
import com.example.jacobcovey.gamestates.YourFirstTurn;
import com.example.jacobcovey.gamestates.YourTurn;
import com.example.jacobcovey.gamestates.iGameBoardState;
import com.example.jacobcovey.model.ClientPresenterFacade;
import com.example.jacobcovey.ticket_to_ride.R;


import java.util.List;
import java.util.Observable;


import shared.classes.Turn;

import static com.example.jacobcovey.constants.Constants.CLAIMING_ROUTE;
import static com.example.jacobcovey.constants.Constants.DESTINATION_CARDS_DRAWN;
import static com.example.jacobcovey.constants.Constants.FIRST_TURN;
import static com.example.jacobcovey.constants.Constants.NOT_YOUR_TURN;
import static com.example.jacobcovey.constants.Constants.ONE_TRAIN_CARD_SELECTED;
import static com.example.jacobcovey.constants.Constants.YOUR_TURN;
import static shared.classes.Turn.TurnState.DESTINATIONCARDSDRAWN;
import static shared.classes.Turn.TurnState.FIRSTTURN;
import static shared.classes.Turn.TurnState.ONETRAINCARDSELECTED;


/**
 * Created by Riley on 5/31/2017.
 */

public class GameBoardPresenter implements iGameBoardPresenter, iGameBoardState {

    private iGameBoardView boardView;
    private iGameBoardState state;

    private ClientPresenterFacade cpf;

    private boolean viewCreated;

    public GameBoardPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        if (boardView == null) {
            return;
        }
//        mRoutes = cpf.getRoutes();
//        updateBoard();
        determineState();
    }

    @Override
    public void setGameBoardView(iGameBoardView gameBoardView) {
        this.boardView = gameBoardView;
    }

    @Override
    public void setViewCreated(boolean viewCreated) {
        this.viewCreated = viewCreated;
        determineState();
    }

    @Override
    public void updateBoard() {
        boardView.updateRoutes(cpf.getRoutes());
    }

    @Override
    public void updateClientRoot(List<Route> routes) {

    }

    @Override
    public boolean setDrawTrainButton(String text, boolean enable) {
        if (!viewCreated) {
            return false;
        }
        boardView.setDrawTrainButtonText(text);
        boardView.setDrawTrainButtonEnable(enable);
        return true;
    }

    @Override
    public boolean setDrawDestinationButton(String text, boolean enable) {
        if (!viewCreated) {
            return false;
        }
        boardView.setDrawDestinationButtonText(text);
        boardView.setDrawDestinationButtonEnable(enable);
        return true;
    }

    @Override
    public boolean setClaimRouteButton(String text, boolean enable) {
        if (!viewCreated) {
            return false;
        }
        boardView.setClaimRouteButtonText(text);
        boardView.setClaimRouteButtonEnable(enable);
        return true;
    }


    @Override
    public void drawDestinationCardsButtonPressed() {
        state.drawDestinationCardsButtonPressed();
    }

    @Override
    public void drawTrainCardsButtonPressed() {
        state.drawTrainCardsButtonPressed();
    }

    @Override
    public void claimRouteButtonPressed() {
        state.claimRouteButtonPressed();
    }

    @Override
    public String getStateName() {
        if (state == null) {
            return "";
        }
        return state.getStateName();
    }

    @Override
    public void closeTrainCardDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeTrainCardDrawer();
    }

    @Override
    public void closeDestinationCardDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeDestinationCardDrawer();
    }

    @Override
    public void closeGameInfoDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeGameInfoDrawer();
    }

    @Override
    public void closeChatDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeChatDrawer();
    }

    @Override
    public void closeHistoryDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeHistoryDrawer();
    }

    @Override
    public void presentTrainCardDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeDrawers();
        boardView.presentTrainCardDrawer();
    }

    @Override
    public void presentDestinationCardDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeDrawers();
        boardView.presentDestinationCardDrawer();
    }

    @Override
    public void presentGameInfoDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeDrawers();
        boardView.presentGameInfoDrawer();
    }

    @Override
    public void presentChatDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeDrawers();
        boardView.presentChatDrawer();
    }

    @Override
    public void presentHistoryDrawer() {
        if (!viewCreated) {
            return;
        }
        boardView.closeDrawers();
        boardView.presentHistoryDrawer();
    }

    @Override
    public boolean onMapTouch(View view, MotionEvent event) {
        if (state != null && state.getStateName().equals(CLAIMING_ROUTE)) {
            PointF current = new PointF(event.getX(), event.getY());

            switch (event.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
//                      Log.i(TAG, current.x + " " + current.y);
                    TouchHandler th = new TouchHandler(current);
                    Route closest = th.getClosestRoute(cpf.getRoutes());
                    createClaimRouteConfirmationDialog(closest);
//                      Log.i(TAG, "onTouchEvent: " + closest.getId());
                    break;

            }
            return true;
        }
        return true;
    }

    private void createClaimRouteConfirmationDialog(final Route closest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(boardView.getActivity());
        CharSequence text = "Do You Want To Claim The Route Connecting  " + closest.getCity1()
                + " and " + closest.getCity2() + "?";
        builder.setTitle(text);
        text = "No";
        builder.setNegativeButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        text = "Yes";
        builder.setPositiveButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                createClaimRouteOptionsDialog(closest);
            }
        });
        builder.create().show();
    }

    private void createClaimRouteOptionsDialog(final Route closest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(boardView.getActivity());
        CharSequence text = "This Needs to be implemented, but I got to this point..."; //TODO: Implement better
        builder.setTitle(text);
        LayoutInflater inflater = boardView.getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.claim_route_options, null);
        builder.setView(v);
        text = "Cancel";
        builder.setNegativeButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        text = "Confirm";
        builder.setPositiveButton(text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO: Check to make sure everything is kosher 
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void setState(iGameBoardState state) {
        this.state = state;
    }

    private void determineState() {
        if (cpf.isMyTurn()) {
            Turn.TurnState turnState = cpf.getTurn().getState();
            if (turnState == FIRSTTURN) {
                if (getStateName().equals(FIRST_TURN)) {
                    return;
                }
                setState(new YourFirstTurn(this));
                return;
            } else if (turnState == DESTINATIONCARDSDRAWN) {
                if (getStateName().equals(DESTINATION_CARDS_DRAWN)) {
                    return;
                }
                setState(new DestinationCardsDrawnTurn(this));
                return;
            } else if (turnState == ONETRAINCARDSELECTED) {
                if (getStateName().equals(ONE_TRAIN_CARD_SELECTED)) {
                    return;
                }
                setState(new OneTrainCardSelectedTurn(this));
                return;
            }
            if (getStateName().equals(YOUR_TURN)) {
                return;
            }
            setState(new YourTurn(this));
            return;
        }
        if (getStateName().equals(NOT_YOUR_TURN)) {
            return;
        }
        setState(new NotYourTurn(this));
    }
}

