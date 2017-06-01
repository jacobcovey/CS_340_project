package com.example.jacobcovey.Views;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacobcovey.Presenters.DestinationPickerPresenter;
import com.example.jacobcovey.Presenters.iDestinationPickerPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import shared.classes.DestinationCard;

/**
 * Created by billrichards on 5/31/17.
 */

public class DestinationPickerView extends Fragment implements iDestinationPickerView {

    private iDestinationPickerPresenter destinationPickerPresenter;

    private DestinationCard card0, card1, card2;
    private CheckBox cardCheckBox0, cardCheckBox1, cardCheckBox2;
    private TextView destinationMessage;
    private Button destinationsSelectedButton;
    private Button closeButton;
    private final String DEFAULT_MESSAGE = "No Destination Card Set";

    public interface DestinationCardDrawerContainer {
        public void closeDestinationCardDrawer();
    }

    private DestinationCardDrawerContainer destinationCardDrawerContainer;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {

            destinationCardDrawerContainer = (DestinationCardDrawerContainer) activity;

        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + " must implement DestinationCardDrawerContainer");

        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        destinationPickerPresenter = new DestinationPickerPresenter();
        destinationPickerPresenter.setDestinationView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.destination_picker_fragment, container, false);
        cardCheckBox0 = (CheckBox) v.findViewById(R.id.destination0);
        cardCheckBox1 = (CheckBox) v.findViewById(R.id.destination1);
        cardCheckBox2 = (CheckBox) v.findViewById(R.id.destination2);

        cardCheckBox0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                destinationPickerPresenter.onChecked(0, b);
            }
        });
        cardCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                destinationPickerPresenter.onChecked(1, b);
            }
        });
        cardCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                destinationPickerPresenter.onChecked(2, b);
            }
        });

        destinationMessage = (TextView) v.findViewById(R.id.destinationMessage);
        destinationsSelectedButton = (Button) v.findViewById(R.id.destinationsSelectedButton);
        destinationsSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationPickerPresenter.selectDestinations();
            }
        });

        closeButton = (Button) v.findViewById(R.id.destinationcard_drawer_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardDrawerContainer.closeDestinationCardDrawer();
            }
        });
        destinationPickerPresenter.setViewCreated(true);
        destinationPickerPresenter.onChecked(0, false);
        return v;
    }

    @Override
    public void enableSelectButton(boolean enable) {
        destinationsSelectedButton.setEnabled(enable);
    }

    @Override
    public void enableCheckBoxes(boolean enable) {
        cardCheckBox0.setEnabled(enable);
        cardCheckBox1.setEnabled(enable);
        cardCheckBox2.setEnabled(enable);
    }

    @Override
    public void setMessage(String message) {
        destinationMessage.setText(message);
    }

    @Override
    public void setDestination0(DestinationCard destinationCard) {
        card0 = destinationCard;
        if (destinationCard != null) {
            cardCheckBox0.setText(destinationCard.getCity1().getName() + " to " + destinationCard.getCity2().getName() + " - " + destinationCard.getPoints() + " Points");
        } else {
            cardCheckBox0.setText(DEFAULT_MESSAGE);
        }
    }

    @Override
    public void setDestination1(DestinationCard destinationCard) {
        card1 = destinationCard;
        if (destinationCard != null) {
            cardCheckBox1.setText(destinationCard.getCity1().getName() + " to " + destinationCard.getCity2().getName() + " - " + destinationCard.getPoints() + " Points");
        } else {
            cardCheckBox1.setText(DEFAULT_MESSAGE);
        }
    }

    @Override
    public void setDestination2(DestinationCard destinationCard) {
        card2 = destinationCard;
        if (destinationCard != null) {
            cardCheckBox2.setText(destinationCard.getCity1().getName() + " to " + destinationCard.getCity2().getName() + " - " + destinationCard.getPoints() + " Points");
        } else {
            cardCheckBox2.setText(DEFAULT_MESSAGE);
        }
    }

    @Override
    public DestinationCard getDestination0() {
        return card0;
    }

    @Override
    public DestinationCard getDestination1() {
        return card1;
    }

    @Override
    public DestinationCard getDestination2() {
        return card2;
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeDestinationDrawer() {
        destinationCardDrawerContainer.closeDestinationCardDrawer();
    }
}
