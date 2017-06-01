package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.ChatMessage;
import shared.classes.CommandData;
import shared.classes.HistoryAction;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/31/2017.
 */

public class UpdateHistory implements iCommand {
    HistoryAction data;

    public UpdateHistory(CommandData data) {
        this.data = (HistoryAction) data.getData();
    }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.addHistoryAction(data);
        return null;
    }
}
