package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.iTrainCardDrawerView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.Observable;

import shared.classes.TrainCard;

/**
 * Created by billrichards on 5/24/17.
 */

public class TrainCardDrawerPresenter implements iTrainCardDrawerPresenter {

    private iTrainCardDrawerView trainCardDrawerView;
    private ClientPresenterFacade cpf;
    private boolean viewCreated;
    private boolean requestExecuting;

    public TrainCardDrawerPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
        viewCreated = false;
        requestExecuting = false;
    }

    @Override
    public void setTrainCardDrawerView(iTrainCardDrawerView trainCardDrawerView) {
        this.trainCardDrawerView = trainCardDrawerView;
    }

    @Override
    public void setViewCreated(Boolean viewCreated) {
        this.viewCreated = viewCreated;
    }

    @Override
    public void pickFaceUpCard(int index) {
        if (cpf.isMyTurn() && !requestExecuting) {
            requestExecuting = true;
            TrainCard card = null;
            switch (index) {
                case 0:
                    trainCardDrawerView.getCard0();
                    break;
                case 1:
                    trainCardDrawerView.getCard1();
                    break;
                case 2:
                    trainCardDrawerView.getCard2();
                    break;
                case 3:
                    trainCardDrawerView.getCard3();
                    break;
                case 4:
                    trainCardDrawerView.getCard4();
                    break;
            }
            pickFaceUpCardRequest pickFaceUpCardRequest = new pickFaceUpCardRequest();
            pickFaceUpCardRequest.execute(card);
            return;
        }
        trainCardDrawerView.displayToast("It's not your turn");
    }

    @Override
    public void drawFaceDownCard() {
        if (cpf.isMyTurn() && !requestExecuting) {
            requestExecuting = true;
            drawFaceDownCardRequest drawFaceDownCardRequest = new drawFaceDownCardRequest();
            drawFaceDownCardRequest.execute();
            return;
        }
        trainCardDrawerView.displayToast("It's not your turn");
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!viewCreated) {
            return;
        }
        TrainCard[] faceUpDeck = cpf.getFaceUpDeck();
        trainCardDrawerView.setCard0(faceUpDeck[0]);
        trainCardDrawerView.setCard1(faceUpDeck[1]);
        trainCardDrawerView.setCard2(faceUpDeck[2]);
        trainCardDrawerView.setCard3(faceUpDeck[3]);
        trainCardDrawerView.setCard4(faceUpDeck[4]);
    }


    private class pickFaceUpCardRequest extends AsyncTask<TrainCard, Integer, Boolean> {

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

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            requestExecuting = false;
        }
    }

    private class drawFaceDownCardRequest extends AsyncTask<Void, Integer, Boolean> {

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

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            requestExecuting = false;
            if (success) {
                trainCardDrawerView.displayToast("Card Drawn");
            }
        }
    }

}
