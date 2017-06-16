package shared.interfaces;

import java.io.IOException;
import java.util.List;

import shared.classes.CommandData;

public interface iCommand {

    Object data = null;

    public List<CommandData> execute() throws IOException;

}
