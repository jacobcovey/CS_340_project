package com.example.jacobcovey.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jacobcovey.ticket_to_ride.R;

import java.util.List;

import shared.classes.DestinationCard;

/**
 * Created by jacobcovey on 5/25/17.
 */

public class GameInfoAdapter extends RecyclerView.Adapter<GameInfoAdapter.ViewHolder> {

    List<DestinationCard> routes;

    protected static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView routTextView;
        public DestinationCard rout;


        public ViewHolder(View view) {
            super(view);

            routTextView = (TextView) view.findViewById(R.id.rout_text_textview);

        }
    }

    public GameInfoAdapter(List<DestinationCard> routes) {
        this.routes = routes;
    }

    public void updateRoutes(List<DestinationCard> routes) {
        this.routes = routes;
    }

    @Override
    public GameInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_route, parent, false);
        view.setBackground(parent.getResources().getDrawable(R.drawable.border));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameInfoAdapter.ViewHolder holder, int position) {
        DestinationCard rout = routes.get(position);
        GameInfoAdapter.ViewHolder vh = (GameInfoAdapter.ViewHolder) holder;
        vh.routTextView.setText(rout.toString());
        vh.rout = rout;
    }

    @Override
    public int getItemCount() {
        if (routes == null) {
            return  0;
        }
        return routes.size();
    }


}
