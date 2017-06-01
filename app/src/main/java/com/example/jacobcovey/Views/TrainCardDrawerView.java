package com.example.jacobcovey.Views;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jacobcovey.Presenters.TrainCardDrawerPresenter;
import com.example.jacobcovey.Presenters.iTrainCardDrawerPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import shared.classes.TrainCard;

/**
 * Created by billrichards on 5/24/17.
 */

public class TrainCardDrawerView extends Fragment implements iTrainCardDrawerView {

    private iTrainCardDrawerPresenter trainCardDrawerPresenter;

    private ImageButton faceDownDeckButton;
    private ImageButton faceUpCardButton0, faceUpCardButton1, faceUpCardButton2, faceUpCardButton3, faceUpCardButton4;
    private Button closeButton;
    private TrainCard faceUpCard0, faceUpCard1, faceUpCard2, faceUpCard3, faceUpCard4;

    public interface TrainCardDrawerContainer {
        public void closeTrainCardDrawer();
    }

    private TrainCardDrawerContainer trainCardDrawerContainer;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {

            trainCardDrawerContainer = (TrainCardDrawerContainer) activity;

        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement TrainCardDrawerContainer");

        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainCardDrawerPresenter = new TrainCardDrawerPresenter();
        trainCardDrawerPresenter.setTrainCardDrawerView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.train_card_drawer, container, false);
        trainCardDrawerPresenter.setViewCreated(true);
        faceUpCardButton0 =(ImageButton) v.findViewById(R.id.faceUpCard0);
        faceUpCardButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerPresenter.pickFaceUpCard(0);
            }
        });
        faceUpCardButton1 =(ImageButton) v.findViewById(R.id.faceUpCard1);
        faceUpCardButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerPresenter.pickFaceUpCard(1);
            }
        });
        faceUpCardButton2 =(ImageButton) v.findViewById(R.id.faceUpCard2);
        faceUpCardButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerPresenter.pickFaceUpCard(2);
            }
        });
        faceUpCardButton3 =(ImageButton) v.findViewById(R.id.faceUpCard3);
        faceUpCardButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerPresenter.pickFaceUpCard(3);
            }
        });
        faceUpCardButton4 =(ImageButton) v.findViewById(R.id.faceUpCard4);
        faceUpCardButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerPresenter.pickFaceUpCard(4);
            }
        });
        faceDownDeckButton =(ImageButton) v.findViewById(R.id.faceDownDeck);
        faceDownDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerPresenter.drawFaceDownCard();
            }
        });
        closeButton = (Button) v.findViewById(R.id.traincard_drawer_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainCardDrawerContainer.closeTrainCardDrawer();
            }
        });
        faceUpCard0 = null;
        faceUpCard1 = null;
        faceUpCard2 = null;
        faceUpCard3 = null;
        faceUpCard4 = null;
        return v;
    }

    @Override
    public TrainCard getCard0() {
        return faceUpCard0;
    }

    @Override
    public TrainCard getCard1() {
        return faceUpCard1;
    }

    @Override
    public TrainCard getCard2() {
        return faceUpCard2;
    }

    @Override
    public TrainCard getCard3() {
        return faceUpCard3;
    }

    @Override
    public TrainCard getCard4() {
        return faceUpCard4;
    }

    @Override
    public void setCard0(TrainCard card) {
        faceUpCard0 = card;
        faceUpCardButton0.setImageDrawable(getImageForCard(card));
    }

    @Override
    public void setCard1(TrainCard card) {
        faceUpCard1 = card;
        faceUpCardButton1.setImageDrawable(getImageForCard(card));
    }

    @Override
    public void setCard2(TrainCard card) {
        faceUpCard2 = card;
        faceUpCardButton2.setImageDrawable(getImageForCard(card));
    }

    @Override
    public void setCard3(TrainCard card) {
        faceUpCard3 = card;
        faceUpCardButton3.setImageDrawable(getImageForCard(card));
    }

    @Override
    public void setCard4(TrainCard card) {
        faceUpCard4 = card;
        faceUpCardButton4.setImageDrawable(getImageForCard(card));
    }

    private Drawable getImageForCard(TrainCard card) {
        if (card == null) {
            return getResources().getDrawable(R.drawable.tickettoride_traincardback);
        }
        switch (card.getColor()) {
            case RED:
                return getResources().getDrawable(R.drawable.tickettoride_redcard);
            case ORANGE:
                return getResources().getDrawable(R.drawable.tickettoride_orangecard);
            case YELLOW:
                return getResources().getDrawable(R.drawable.tickettoride_yellowcard);
            case GREEN:
                return getResources().getDrawable(R.drawable.tickettoride_greencard);
            case BLUE:
                return getResources().getDrawable(R.drawable.tickettoride_bluecard);
            case PURPLE:
                return getResources().getDrawable(R.drawable.tickettoride_purplecard);
            case WHITE:
                return getResources().getDrawable(R.drawable.tickettoride_whitecard);
            case BLACK:
                return getResources().getDrawable(R.drawable.tickettoride_blackcard);
            case WILD:
                return getResources().getDrawable(R.drawable.tickettoride_wildcard);
            default:
                return getResources().getDrawable(R.drawable.tickettoride_traincardback);
        }
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableAllCards(boolean enable) {
        faceUpCardButton0.setEnabled(enable);
        faceUpCardButton1.setEnabled(enable);
        faceUpCardButton2.setEnabled(enable);
        faceUpCardButton3.setEnabled(enable);
        faceUpCardButton4.setEnabled(enable);
        faceDownDeckButton.setEnabled(enable);
    }

    @Override
    public void enableCard0(boolean enable) {
        faceUpCardButton0.setEnabled(enable);
    }

    @Override
    public void enableCard1(boolean enable) {
        faceUpCardButton1.setEnabled(enable);
    }

    @Override
    public void enableCard2(boolean enable) {
        faceUpCardButton2.setEnabled(enable);
    }

    @Override
    public void enableCard3(boolean enable) {
        faceUpCardButton3.setEnabled(enable);
    }

    @Override
    public void enableCard4(boolean enable) {
        faceUpCardButton4.setEnabled(enable);
    }

    @Override
    public void enableDeck(boolean enable) {
        faceDownDeckButton.setEnabled(enable);
    }

}
