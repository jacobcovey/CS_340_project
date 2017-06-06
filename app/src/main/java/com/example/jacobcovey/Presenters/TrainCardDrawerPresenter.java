package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.iTrainCardDrawerView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.Observable;

import shared.classes.TrainCard;
import shared.classes.TrainCardColors;

import static shared.classes.Turn.TurnState.FIRSTTURN;
import static shared.classes.Turn.TurnState.ONETRAINCARDSELECTED;

/**
 * Created by billrichards on 5/24/17.
 */

public class TrainCardDrawerPresenter implements iTrainCardDrawerPresenter {

    /**
     * This is the member used to call things on the view
     */
    private iTrainCardDrawerView trainCardDrawerView;

    /**
     * Copy of ClientPresenterFacade to call methods to send commands to server, and get data from model
     */
    private ClientPresenterFacade cpf;

    /**
     * boolean to determine if the view has been inflated (set by the view)
     * This helps to prevent any methods that update the view from being called prematurely
     */
    private boolean viewCreated;

    /**
     * boolean to prevent user from drawing another card while the server is processing the first draw
     */
    private boolean requestExecuting;

    /**
     * Initializes viewCreated to false, requestExecuting to false, and cpf to a new ClientPresenterFacade
     * Adds self as observer of ClientModelRoot
     * @pre ClientModelRoot._instance =! null
     * @post cpf != null
     * @post this is added to the observers of ClientModelRoot
     * @post viewCreated == false
     * @post requestExecuting == false
     */
    public TrainCardDrawerPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
        viewCreated = false;
        requestExecuting = false;
    }

    /**
     * Sets the trainCardDrawerView (can be null)
     * @param trainCardDrawerView
     * @pre none
     * @post this.trainCardDrawerView == trainCardDrawerView
     */
    @Override
    public void setTrainCardDrawerView(iTrainCardDrawerView trainCardDrawerView) {
        this.trainCardDrawerView = trainCardDrawerView;
    }

    /**
     * Sets viewCreated
     * @param viewCreated
     * @pre viewCreated != null
     * @post this.viewCreated == viewCreated
     */
    @Override
    public void setViewCreated(Boolean viewCreated) {
        this.viewCreated = viewCreated;
    }

    /**
     * From the given index, gets the corresponding card from the view,
     * and then sends a pickFaceUpCard request to the server via pickFaceUpCardRequest AsyncTask
     * Method only executes if it is user's turn, and there is not another request executing
     * @param index
     * @pre this.trainCardDrawerView != null
     * @pre viewCreated == true
     * @post if isMyTurn && !requestExecuting card[index] == null
     * @post if isMyTurn && !requestExecuting requestExecuting == true
     * @post if isMyTurn && !requestExecuting pickFaceUpCardRequest execution has begun
     * @post if requestExecuting toast displayed to user "Waiting for server..."
     * @post if !isMyTurn && !requestExecuting toast displayed to user "It's not your turn"
     */
    @Override
    public void pickFaceUpCard(int index) {
        System.out.println("pick face up card called on cards " + index);
        if (cpf.isMyTurn() && !requestExecuting) {
            requestExecuting = true;
            TrainCard card = null;
            switch (index) {
                case 0:
                    card = trainCardDrawerView.getCard0();
                    trainCardDrawerView.setCard0(null);
                    break;
                case 1:
                    card = trainCardDrawerView.getCard1();
                    trainCardDrawerView.setCard1(null);
                    break;
                case 2:
                    card = trainCardDrawerView.getCard2();
                    trainCardDrawerView.setCard2(null);
                    break;
                case 3:
                    card = trainCardDrawerView.getCard3();
                    trainCardDrawerView.setCard3(null);
                    break;
                case 4:
                    card = trainCardDrawerView.getCard4();
                    trainCardDrawerView.setCard4(null);
                    break;
            }
            if (card != null) {
                pickFaceUpCardRequest pickFaceUpCardRequest = new pickFaceUpCardRequest();
                pickFaceUpCardRequest.execute(card);
            }
            return;
        }
        if (requestExecuting) {
            trainCardDrawerView.displayToast("Waiting for server...");
        } else {
            trainCardDrawerView.displayToast("It's not your turn");
        }
    }

    /**
     * Sends a drawFaceDownCard request to the server via drawFaceDownCardRequest AsyncTask
     * Method only executes if it is user's turn, and there is not another request executing
     * @pre None
     * @post if isMyTurn && !requestExecuting requestExecuting == true
     * @post if isMyTurn && !requestExecuting drawFaceDownCardRequest execution has begun
     * @post if requestExecuting toast displayed to user "Waiting for server..."
     * @post if !isMyTurn && !requestExecuting toast displayed to user "It's not your turn"
     */
    @Override
    public void drawFaceDownCard() {
        System.out.println("Draw face down card called");
        if (cpf.isMyTurn() && !requestExecuting) {
            requestExecuting = true;
            drawFaceDownCardRequest drawFaceDownCardRequest = new drawFaceDownCardRequest();
            drawFaceDownCardRequest.execute();
            return;
        }
        if (requestExecuting) {
            trainCardDrawerView.displayToast("Waiting for server...");
        } else {
            trainCardDrawerView.displayToast("It's not your turn");
        }
    }

    /**
     * Removes this from ClientModelRoot Observers - this is called when the fragment is being dismissed
     * @pre cpf != null
     * @pre this is an observer of the ClientModelRoot
     * @post this is not an observer of ClientModelRoot
     */
    @Override
    public void removeObserver() {
        cpf.removeObserver(this);
    }

    /**
     * Called then ClientModelRoot is changed. Calls setFaceUpDeck
     * @param observable
     * @param o
     * @pre viewCreated == true
     * @post setFaceUpDeck method has run
     */
    @Override
    public void update(Observable observable, Object o) {
        if (!viewCreated) {
            return;
        }
        setFaceUpDeck();
    }

    /**
     * Sets the faceUpDeck on the trainCardDrawerView, then calls setEnableForCards()
     * This method either sets the face up deck to what is in the Model or to null if the Model has no value
     * @pre cpf != null
     * @pre trainCardDrawerView != null
     * @pre viewCreated == true
     * @post trainCardDrawerView cards are set
     * @post setEnableForCards method has run
     */
    @Override
    public void setFaceUpDeck() {
        TrainCard[] faceUpDeck = cpf.getFaceUpDeck();
        trainCardDrawerView.enableAllCards(true);
        if (faceUpDeck != null) {
            trainCardDrawerView.setCard0(faceUpDeck[0]);
            trainCardDrawerView.setCard1(faceUpDeck[1]);
            trainCardDrawerView.setCard2(faceUpDeck[2]);
            trainCardDrawerView.setCard3(faceUpDeck[3]);
            trainCardDrawerView.setCard4(faceUpDeck[4]);
        } else {
            trainCardDrawerView.setCard0(null);
            trainCardDrawerView.setCard1(null);
            trainCardDrawerView.setCard2(null);
            trainCardDrawerView.setCard3(null);
            trainCardDrawerView.setCard4(null);
        }
        setEnableForCards();
    }

    /**
     * Determines which cards to prevent user from selecting cards they shouldn't
     * @pre trainCardDrawerView != null
     * @pre viewCreated == true
     * @post only appropriate face up cards are selectable
     */
    private void setEnableForCards() {
       if (!cpf.isMyTurn() || cpf.getTurn().getState() == FIRSTTURN) {
           disableAllCards();
           return;
       }
        disableNullCards();
        if (cpf.getTurn().getState() == ONETRAINCARDSELECTED) {
            disableRainbowCards();
        }
    }

    /**
     * Disables any cards that are wild so that users cannot select them
     * @pre trainCardDrawerView != null
     * @pre viewCreated == true
     * @post all rainbow cards are disabled
     */
    private void disableRainbowCards() {
        TrainCard card = null;
        card = trainCardDrawerView.getCard0();
        trainCardDrawerView.enableCard0(card == null ? false : card.getColor() != TrainCardColors.WILD);
        card = trainCardDrawerView.getCard1();
        trainCardDrawerView.enableCard1(card == null ? false : card.getColor() != TrainCardColors.WILD);
        card = trainCardDrawerView.getCard2();
        trainCardDrawerView.enableCard2(card == null ? false : card.getColor() != TrainCardColors.WILD);
        card = trainCardDrawerView.getCard3();
        trainCardDrawerView.enableCard3(card == null ? false : card.getColor() != TrainCardColors.WILD);
        card = trainCardDrawerView.getCard4();
        trainCardDrawerView.enableCard4(card == null ? false : card.getColor() != TrainCardColors.WILD);
    }

    /**
     * Disables any cards that are so that users cannot select them
     * @pre trainCardDrawerView != null
     * @pre viewCreated == true
     * @post all null cards are disabled
     */
    private void disableNullCards() {
        TrainCard card = null;
        card = trainCardDrawerView.getCard0();
        trainCardDrawerView.enableCard0(card != null );
        card = trainCardDrawerView.getCard1();
        trainCardDrawerView.enableCard1(card != null);
        card = trainCardDrawerView.getCard2();
        trainCardDrawerView.enableCard2(card != null);
        card = trainCardDrawerView.getCard3();
        trainCardDrawerView.enableCard3(card != null);
        card = trainCardDrawerView.getCard4();
        trainCardDrawerView.enableCard4(card != null);
    }

    private void disableAllCards() {
        trainCardDrawerView.enableAllCards(false);
    }

    /**
     * Async task for sending a pickFaceUpCard request to the server
     */
    private class pickFaceUpCardRequest extends AsyncTask<TrainCard, Integer, Boolean> {

        /**
         * Sends pickFaceUpCard to server, returns true is successful, false if error
         * @param params
         * @return success
         * @pre params.length == 1
         * @pre trainCardDrawerView != null
         * @post request will have been sent to server
         */
        @Override
        protected Boolean doInBackground(TrainCard... params) {
            try {
                cpf.pickFaceUpCard(params[0]);
            } catch (IOException e) {
                System.err.printf(e.getMessage());
                trainCardDrawerView.displayToast(e.getMessage());
                return false;
            }
            return true;
        }

        /**
         * Sets requestExecuting to false and the request was successful calls disableRainbowCards
         * @param success
         * @pre None
         * @post disableRainbowCards method has run
         */
        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            requestExecuting = false;
            if (success) {
                disableAllCards();
            }
        }
    }

    /**
     * Async task for sending a drawFaceDownCard request to the server
     */
    private class drawFaceDownCardRequest extends AsyncTask<Void, Integer, Boolean> {

        /**
         * Sends drawFaceDownCard to server, returns true is successful, false if error
         * @param params
         * @return success
         * @pre trainCardDrawerView != null
         * @post request will have been sent to server
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                cpf.drawFaceDownCard();
            } catch (IOException e) {
                System.err.printf(e.getMessage());
                trainCardDrawerView.displayToast(e.getMessage());
                return false;
            }
            return true;
        }

        /**
         * Sets requestExecuting to false and the request was successful calls disableRainbowCards
         * @param success
         * @pre trainCardDrawerView != null
         * @post disableRainbowCards method has run
         */
        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            requestExecuting = false;
            if (success) {
                trainCardDrawerView.displayToast("Card Drawn");
                disableAllCards();
            }
        }
    }

}
