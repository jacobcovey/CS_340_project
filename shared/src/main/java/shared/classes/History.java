package shared.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billrichards on 5/24/17.
 */

public class History {

    List<HistoryAction> actions = new ArrayList<>();

    public History(List<HistoryAction> actions) {
        this.actions = actions;
    }

    public List<HistoryAction> getActions() {
        return actions;
    }

    public void addAction(HistoryAction action) {
        actions.add(action);
    }

}
