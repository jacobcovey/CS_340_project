package com.example.jacobcovey.communication;

import android.os.AsyncTask;
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
        PollAsync asyncPoller = new PollAsync();

        switch (ClientFacade._instance.getState()) {

            case LOGIN:
                return;
            case GAMELIST:
                asyncPoller.execute(new CommandData(CommandData.Type.UPDATEGAMELIST, "Game List Please"));
                return;
            case GAMECREATION:
                return;
            case GAMELOBBY:
                asyncPoller.execute(new CommandData(CommandData.Type.UPDATECURRENTGAME, "Player List Please"));
                return;
            case GAMESTARTED:
                return;

        }

    }


    private class PollAsync extends AsyncTask<CommandData, Integer, Void> {

        @Override
        protected Void doInBackground(CommandData... commandData) {
            try {
                ServerProxy._instance.executeCommand(commandData[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
