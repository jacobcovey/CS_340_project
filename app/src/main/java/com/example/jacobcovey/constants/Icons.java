package com.example.jacobcovey.constants;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.jacobcovey.ticket_to_ride.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

/**
 * Created by billrichards on 5/30/17.
 */

public class Icons {
    public static Drawable chatIcon,
            historyIcon,
            gameInfoIcon;

    public Icons(Activity activity) {
        chatIcon = new IconDrawable(activity, Iconify.IconValue.fa_comments_o).colorRes(R.color.white).sizeDp(40);
        historyIcon = new IconDrawable(activity, Iconify.IconValue.fa_history).colorRes(R.color.white).sizeDp(40);
        gameInfoIcon = new IconDrawable(activity, Iconify.IconValue.fa_info_circle).colorRes(R.color.white).sizeDp(40);
    }
}
