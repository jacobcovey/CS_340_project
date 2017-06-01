package com.example.jacobcovey.communication;

import android.os.AsyncTask;

import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.ClientModelRoot;

import java.io.IOException;
import java.util.TimerTask;

import shared.classes.CommandData;

/**
 * Created by billrichards on 5/17/17.
 */

public class Poller extends TimerTask {


    public Poller() {}

    @Override
    public void run() {
        poll();
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
                asyncPoller.execute(new CommandData(CommandData.Type.UPDATECURRENTGAME, ClientFacade._instance.getCurrentGame()));
                return;
            case GAMESTARTED:
                asyncPoller.execute(new CommandData(CommandData.Type.GETOUTSTANDINGCOMMANDS, ClientModelRoot._instance.getUser().getUsername()));
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
