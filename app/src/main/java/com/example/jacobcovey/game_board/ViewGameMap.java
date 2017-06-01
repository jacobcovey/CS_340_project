package com.example.jacobcovey.game_board;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.jacobcovey.ticket_to_ride.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Riley on 5/31/2017.
 */

public class ViewGameMap extends View {

    public static final String TAG = "BoardView";

    private List<Route> mRoutes = new ArrayList<>();
    private Paint mRoutePaint;

    public ViewGameMap(Context context) {
        this(context, null);
    }

    public ViewGameMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mRoutePaint = new Paint();
        mRoutes = RouteLoader.loadRoutes();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, current.x + " " + current.y);
                break;

        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //draw the map
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.game_board);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), true);
        canvas.drawBitmap(scaled, 0, 0, null);

        //draw the routes
        if (mRoutes != null) {

            for (Route r : mRoutes) {

                mRoutePaint.setColor(r.getColor());
                for (PointF point : r.getPoints()) {
                    canvas.drawCircle(point.x, point.y, 15, mRoutePaint);
//                Bitmap mybitmap = BitmapFactory.decodeResource(res, R.drawable.ic_train);
//                canvas.drawBitmap(mybitmap, point.x, point.y, mRoutePaint);

//                Drawable trainIcon = new IconDrawable(getContext(), Iconify.IconValue.fa_train).colorRes(R.color.blue).sizeDp(40);
//                canvas.drawPaint(trainIcon);
                }

            }
        }
    }

    public void setRoutes(List<Route> routes) {
        mRoutes = routes;
//        invalidate();
    }
}
