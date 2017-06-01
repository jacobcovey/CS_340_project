package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameBoardView;
import com.example.jacobcovey.game_board.Route;

import java.util.List;
import java.util.Observer;

/**
 * Created by Riley on 5/31/2017.
 */

public interface iGameBoardPresenter extends Observer{
    void setGameBoardView(IGameBoardView gameBoardView);

    void updateBoard();

    void updateClientRoot(List<Route> routes);

    void changeClientRoot();

}
