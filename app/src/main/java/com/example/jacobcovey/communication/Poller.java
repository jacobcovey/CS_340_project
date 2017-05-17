package com.example.jacobcovey.communication;

import android.widget.Switch;

import com.example.jacobcovey.model.ClientFacade;

import java.util.Timer;
import java.util.TimerTask;

import shared.classes.CommandData;

/**
 * Created by billrichards on 5/17/17.
 */

public class Poller extends TimerTask {

    private static Poller _instance = new Poller();

    private Poller() {
        Timer timer = new Timer();
        timer.schedule(_instance, 0, 1000);
    }

    @Override
    public void run() {
        _instance.poll();
    }

    public void poll() {

        switch (ClientFacade._instance.getState()) {

            case LOGIN:
                return;
            case GAMELIST:
                ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.UPDATEGAMELIST, "Game List Please"));
                return;
            case GAMECREATION:
                return;
            case GAMELOBBY:
                ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.UPDATECURRENTGAME, "Player List Please"));
                return;
            case GAMESTARTED:
                return;

        }

    }

}
