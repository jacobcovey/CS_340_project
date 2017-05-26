package com.example.jacobcovey.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jacobcovey.Presenters.GameInfoPresenter;
import com.example.jacobcovey.Presenters.IGameInfoPresenter;
import com.example.jacobcovey.ticket_to_ride.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;

/**
 * Created by jacobcovey on 5/24/17.
 */

public class GameInfoFragment extends Fragment implements IGameInfoView {

    public static final int MAXNUMBEROFPLAYERS = 5;

    IGameInfoPresenter gameInfoPresenter;

    private LinearLayout player1box;
    private LinearLayout player2box;
    private LinearLayout player3box;
    private LinearLayout player4box;
    private LinearLayout player5box;

    private TextView player1NameTextView;
    private TextView player2NameTextView;
    private TextView player3NameTextView;
    private TextView player4NameTextView;
    private TextView player5NameTextView;

    private TextView player1PointsTextView;
    private TextView player2PointsTextView;
    private TextView player3PointsTextView;
    private TextView player4PointsTextView;
    private TextView player5PointsTextView;

    private TextView player1TrainsTextView;
    private TextView player2TrainsTextView;
    private TextView player3TrainsTextView;
    private TextView player4TrainsTextView;
    private TextView player5TrainsTextView;

    private TextView player1CardsTextView;
    private TextView player2CardsTextView;
    private TextView player3CardsTextView;
    private TextView player4CardsTextView;
    private TextView player5CardsTextView;

    private TextView player1RoutesTextView;
    private TextView player2RoutesTextView;
    private TextView player3RoutesTextView;
    private TextView player4RoutesTextView;
    private TextView player5RoutesTextView;

    private TextView numRedCardTextView;
    private TextView numBlueCardTextView;
    private TextView numGreenCardTextView;
    private TextView numPurpleCardTextView;
    private TextView numOrangeCardTextView;
    private TextView numWhiteCardTextView;
    private TextView numBlackCardTextView;
    private TextView numYellowCardTextView;
    private TextView numWildCardTextView;

    private Stack<LinearLayout> playerBoxStack;
    private List<TextView> playerNames = new ArrayList<TextView>();
    private List<TextView> playerPoints = new ArrayList<TextView>();
    private List<TextView> playerTrains = new ArrayList<TextView>();
    private List<TextView> playerCards = new ArrayList<TextView>();
    private List<TextView> playerRoutes = new ArrayList<TextView>();

    private Boolean unusedhidden = false;

    private RecyclerView routesRecyclerView;
    private RecyclerView.Adapter routesAdapter;
    private RecyclerView.LayoutManager routesLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameInfoPresenter = new GameInfoPresenter();
        gameInfoPresenter.setGameListView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_info_fragment, container, false);

        player1box = (LinearLayout) v.findViewById(R.id.game_info_p1_box);
        player2box = (LinearLayout) v.findViewById(R.id.game_info_p2_box);
        player3box = (LinearLayout) v.findViewById(R.id.game_info_p3_box);
        player4box = (LinearLayout) v.findViewById(R.id.game_info_p4_box);
        player5box = (LinearLayout) v.findViewById(R.id.game_info_p5_box);

        playerBoxStack.push(player1box);
        playerBoxStack.push(player2box);
        playerBoxStack.push(player3box);
        playerBoxStack.push(player4box);
        playerBoxStack.push(player5box);

        player1NameTextView = (TextView) v.findViewById(R.id.game_info_p1_name);
        player2NameTextView = (TextView) v.findViewById(R.id.game_info_p2_name);
        player3NameTextView = (TextView) v.findViewById(R.id.game_info_p3_name);
        player4NameTextView = (TextView) v.findViewById(R.id.game_info_p4_name);
        player5NameTextView = (TextView) v.findViewById(R.id.game_info_p5_name);

        playerNames.add(player1NameTextView);
        playerNames.add(player2NameTextView);
        playerNames.add(player3NameTextView);
        playerNames.add(player4NameTextView);
        playerNames.add(player5NameTextView);

        player1PointsTextView = (TextView) v.findViewById(R.id.game_info_p1_points);
        player2PointsTextView = (TextView) v.findViewById(R.id.game_info_p2_points);
        player3PointsTextView = (TextView) v.findViewById(R.id.game_info_p3_points);
        player4PointsTextView = (TextView) v.findViewById(R.id.game_info_p4_points);
        player5PointsTextView = (TextView) v.findViewById(R.id.game_info_p5_points);

        playerPoints.add(player1PointsTextView);
        playerPoints.add(player2PointsTextView);
        playerPoints.add(player3PointsTextView);
        playerPoints.add(player4PointsTextView);
        playerPoints.add(player5PointsTextView);

        player1TrainsTextView = (TextView) v.findViewById(R.id.game_info_p1_trains);
        player2TrainsTextView = (TextView) v.findViewById(R.id.game_info_p2_trains);
        player3TrainsTextView = (TextView) v.findViewById(R.id.game_info_p3_trains);
        player4TrainsTextView = (TextView) v.findViewById(R.id.game_info_p4_trains);
        player5TrainsTextView = (TextView) v.findViewById(R.id.game_info_p5_trains);

        playerTrains.add(player1TrainsTextView);
        playerTrains.add(player2TrainsTextView);
        playerTrains.add(player3TrainsTextView);
        playerTrains.add(player4TrainsTextView);
        playerTrains.add(player5TrainsTextView);

        player1CardsTextView = (TextView) v.findViewById(R.id.game_info_p1_cards);
        player2CardsTextView = (TextView) v.findViewById(R.id.game_info_p2_cards);
        player3CardsTextView = (TextView) v.findViewById(R.id.game_info_p3_cards);
        player4CardsTextView = (TextView) v.findViewById(R.id.game_info_p4_cards);
        player5CardsTextView = (TextView) v.findViewById(R.id.game_info_p5_cards);

        playerCards.add(player1CardsTextView);
        playerCards.add(player2CardsTextView);
        playerCards.add(player3CardsTextView);
        playerCards.add(player4CardsTextView);
        playerCards.add(player5CardsTextView);

        player1RoutesTextView = (TextView) v.findViewById(R.id.game_info_p1_routes);
        player2RoutesTextView = (TextView) v.findViewById(R.id.game_info_p2_routes);
        player3RoutesTextView = (TextView) v.findViewById(R.id.game_info_p3_routes);
        player4RoutesTextView = (TextView) v.findViewById(R.id.game_info_p4_routes);
        player5RoutesTextView = (TextView) v.findViewById(R.id.game_info_p5_routes);

        playerRoutes.add(player1RoutesTextView);
        playerRoutes.add(player2RoutesTextView);
        playerRoutes.add(player3RoutesTextView);
        playerRoutes.add(player4RoutesTextView);
        playerRoutes.add(player5RoutesTextView);

        numRedCardTextView = (TextView) v.findViewById(R.id.game_info_num_red_cards);
        numBlueCardTextView = (TextView) v.findViewById(R.id.game_info_num_blue_cards);
        numGreenCardTextView = (TextView) v.findViewById(R.id.game_info_num_green_cards);
        numPurpleCardTextView = (TextView) v.findViewById(R.id.game_info_num_purple_cards);
        numOrangeCardTextView = (TextView) v.findViewById(R.id.game_info_num_orange_cards);
        numWhiteCardTextView = (TextView) v.findViewById(R.id.game_info_num_white_cards);
        numBlackCardTextView = (TextView) v.findViewById(R.id.game_info_num_black_cards);
        numYellowCardTextView = (TextView) v.findViewById(R.id.game_info_num_yellow_cards);
        numWildCardTextView = (TextView) v.findViewById(R.id.game_info_num_wild_cards);

        routesRecyclerView = (RecyclerView) v.findViewById(R.id.game_info_routes_recyclerView);
        routesRecyclerView.setHasFixedSize(true);

        routesLayoutManager = new LinearLayoutManager(getActivity());

        ((LinearLayoutManager)routesLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        routesRecyclerView.setLayoutManager(routesLayoutManager);

        List<DestinationCard> routes = gameInfoPresenter.getRoutes();

        GameInfoAdapter adapter = new GameInfoAdapter(routes);

        routesRecyclerView.setAdapter(adapter);

        return v;
    }


    @Override
    public void setPlayerInfo(final List<Player> players) {

        hideUnusedPlayers(players.size());

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (int x = 0; x < players.size(); x++ ) {

                    playerNames.get(x).setText(players.get(x).getUserName());
                    playerPoints.get(x).setText((Integer.toString(players.get(x).getPoints())));
                    playerTrains.get(x).setText(Integer.toString(players.get(x).getTrainCars()));
                    playerCards.get(x).setText(Integer.toString(players.get(x).getTrainCards().size()));
                    playerRoutes.get(x).setText(Integer.toString(players.get(x).getDestinationCards().size()));

                }
            }
        });

    }

    public void hideUnusedPlayers(final int numGamePlayers) {
        if (unusedhidden == false) {
            unusedhidden = true;
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    int numToHide = MAXNUMBEROFPLAYERS - numGamePlayers;

                    Stack<LinearLayout> tempStack = playerBoxStack;

                    for (int x = 0; x < numToHide; x++) {
                        LinearLayout box = tempStack.pop();
                        box.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public void setTrainCardsInfo(final List<TrainCard> cards) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

                for (final TrainCard card: cards) {

                    if ( map.containsKey(card.getColor().ordinal())) {
                        Integer value = map.get(card.getColor().ordinal());
                        map.put(card.getColor().ordinal(),value++);
                    } else {
                        map.put(card.getColor().ordinal(), 1);
                    }

                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        TrainCardColors color = TrainCardColors.values()[ (Integer) pair.getKey()];

                        switch (color) {
                            case WHITE:
                                numWhiteCardTextView.setText(pair.getValue().toString());
                                break;
                            case BLACK:
                                numBlackCardTextView.setText(pair.getValue().toString());
                                break;
                            case RED:
                                numRedCardTextView.setText(pair.getValue().toString());
                                break;
                            case ORANGE:
                                numOrangeCardTextView.setText(pair.getValue().toString());
                                break;
                            case YELLOW:
                                numYellowCardTextView.setText(pair.getValue().toString());
                                break;
                            case GREEN:
                                numGreenCardTextView.setText(pair.getValue().toString());
                                break;
                            case BLUE:
                                numBlueCardTextView.setText(pair.getValue().toString());
                                break;
                            case PURPLE:
                                numPurpleCardTextView.setText(pair.getValue().toString());
                                break;
                            case WILD:
                                numWildCardTextView.setText(pair.getValue().toString());
                                break;
                            default:
                                break;
                        }
                    }

                }
            }
        });
    }

    @Override
    public void setRoutesInfo(List<DestinationCard> routes) {

    }
}
