package com.example.jacobcovey.Views;

import shared.classes.History;
import shared.classes.HistoryAction;

/**
 * Created by spencer on 5/25/17.
 */

public interface iGameHistoryView {

    void addHistoryEvent(HistoryAction action);

    void updateHistory(History history);
}
