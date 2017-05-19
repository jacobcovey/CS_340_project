package shared.interfaces;

import java.io.IOException;

import shared.classes.CommandData;

/**
 * Created by billrichards on 5/15/17.
 */

public interface iServer {

    public void executeCommand(CommandData commandData) throws IOException;

}
