package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.iDestinationPickerView;
import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import shared.classes.DestinationCard;
import shared.classes.HistoryAction;
import shared.classes.Turn;

/**
 * Created by billrichards on 5/31/17.
 */

public class DestinationPickerPresenter implements iDestinationPickerPresenter {

    private iDestinationPickerView destinationPickerView;
    private int numRequired, numChecked;
    private boolean card0Checked, card1Checked, card2Checked;
    private ClientPresenterFacade cpf;
    private String message;
    private final int NOTYOURTURN = 100;

    private boolean viewCreated;

    public DestinationPickerPresenter() {
        numChecked = 0;
        card0Checked = false;
        card1Checked = false;
        card2Checked = false;
        viewCreated = false;
        cpf = new ClientPresenterFacade();
        setStateVars();
        if (cpf.isMyTurn() && cpf.getDestCardsToSelectFrom() == null) {
            drawDestinations();
        }
    }

    private void setStateVars() {
        if (cpf.isMyTurn() && cpf.getTurn().getState() == Turn.TurnState.FIRSTTURN) {
            message = "Select at least 2 Destinations";
            numRequired = 2;
        } else if(cpf.isMyTurn()) {
            message = "Select at least 1 Destinations";
            numRequired = 1;
        } else {
            numRequired = NOTYOURTURN;
            message = "It is NOT your turn";
        }
    }

    private void drawDestinations() {
        drawDestinationCardsRequest drawRequest = new drawDestinationCardsRequest();
        drawRequest.execute();
    }



    private void setDestinationCards() {
        if (!viewCreated) {
            return;
        }
        DestinationCard[] cards = cpf.getDestCardsToSelectFrom();
        if (cards != null && cards.length == 3) {
            destinationPickerView.setDestination0(cards[0]);
            destinationPickerView.setDestination1(cards[1]);
            destinationPickerView.setDestination2(cards[2]);
            destinationPickerView.enableCheckBoxes(true);
        } else {
            destinationPickerView.setDestination0(null);
            destinationPickerView.setDestination1(null);
            destinationPickerView.setDestination2(null);
            destinationPickerView.enableCheckBoxes(false);
        }
    }

    @Override
    public void removeObserver() {
        cpf.removeObserver(this);
    }

    @Override
    public void selectDestinations() {
        ArrayList<DestinationCard> cards = new ArrayList<>();
        if (card0Checked) {
            cards.add(destinationPickerView.getDestination0());
        }
        if (card1Checked) {
            cards.add(destinationPickerView.getDestination1());
        }
        if (card2Checked) {
            cards.add(destinationPickerView.getDestination2());
        }
        destinationCardsSelectedRequest selectedRequest = new destinationCardsSelectedRequest();
        DestinationCard[] params = new DestinationCard[cards.size()];
        for (int i = 0; i < cards.size() ; i++) {
            params[i] = cards.get(i);
        }
        selectedRequest.execute(params);
    }

    @Override
    public void setViewCreated(boolean created) {
        this.viewCreated = created;
        destinationPickerView.setMessage(message);
        if (cpf.isMyTurn() && cpf.getDestCardsToSelectFrom() != null) {
            setDestinationCards();
        }
    }

    @Override
    public void setDestinationView(iDestinationPickerView destinationPickerView) {
        this.destinationPickerView = destinationPickerView;
    }

    @Override
    public void onChecked(int index, boolean checked) {
        if (!viewCreated) {
            return;
        }
        switch (index) {
            case 0:
                card0Checked = checked;
                break;
            case 1:
                card1Checked = checked;
                break;
            case 2:
                card2Checked = checked;
                break;
        }
        countCheckedCards();
        if (numChecked >= numRequired) {
            destinationPickerView.enableSelectButton(true);
        } else {
            if (numRequired == NOTYOURTURN) {
                destinationPickerView.enableCheckBoxes(false);
            } else {
                destinationPickerView.enableCheckBoxes(true);
            }
            destinationPickerView.enableSelectButton(false);
        }
    }

    private void countCheckedCards() {
        numChecked = 0;
        numChecked += card0Checked ? 1 : 0;
        numChecked += card1Checked ? 1 : 0;
        numChecked += card2Checked ? 1 : 0;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!viewCreated) {
            return;
        }
        int check = numRequired;
        setStateVars();
        if (check == NOTYOURTURN && numRequired != NOTYOURTURN && cpf.getDestCardsToSelectFrom() == null) {
            drawDestinations();
        } else if (cpf.isMyTurn()) {
            setDestinationCards();
        }
    }

    private class drawDestinationCardsRequest extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                cpf.drawDestinationCards();
            } catch (IOException e) {
                System.err.printf(e.getMessage());
                destinationPickerView.displayToast(e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                setDestinationCards();
            }
        }
    }

    private class destinationCardsSelectedRequest extends AsyncTask<DestinationCard[], Integer, Boolean> {

        @Override
        protected Boolean doInBackground(DestinationCard[]... params) {
            try {
                cpf.destinationCardsPicked(params[0]);
            } catch (IOException e) {
                System.err.printf(e.getMessage());
                destinationPickerView.displayToast(e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                cpf.clearDestCardsToSelectFrom();
                destinationPickerView.closeDestinationDrawer();
            }
        }
    }


}
