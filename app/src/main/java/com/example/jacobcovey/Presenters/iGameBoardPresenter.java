package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameBoardView;
import com.example.jacobcovey.game_board.Route;

import java.util.List;

/**
 * Created by Riley on 5/31/2017.
 */

public interface iGameBoardPresenter {
    void setGameBoardView(IGameBoardView gameBoardView);

    void updateBoard();

    void updateClientRoot(List<Route> routes);

    void changeClientRoot();
}
