package com.example.jacobcovey.communication;

import android.widget.Switch;

import com.example.jacobcovey.model.ClientFacade;

import java.io.IOException;
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
                try {
                    ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.UPDATEGAMELIST, "Game List Please"));
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                return;
            case GAMECREATION:
                return;
            case GAMELOBBY:
                try {
                    ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.UPDATECURRENTGAME, "Player List Please"));
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                return;
            case GAMESTARTED:
                return;

        }

    }

}
