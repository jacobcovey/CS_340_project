package com.example.jacobcovey.commands;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import shared.classes.CommandData;

/**
 * Created by billrichards on 5/18/17.
 */

public class ErrorCommand implements shared.interfaces.iCommand {

    String data;

    public ErrorCommand(CommandData data) {
        this.data = (String) data.getData();
    }

    @Override
    public List<CommandData> execute() throws IOException {
        throw new IOException(data);
    }

}
