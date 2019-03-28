package hw.appdev.example.android.assignment4;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //private LayoutInflater mInflater;
    private Context mContext;
    List<Circle> mData;
    CircleAnimationView circle;

    public RecyclerViewAdapter(Context context, List<Circle> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.recyclerview_row, null);
        Log.i("What values are in the list?", "Test values"+mData.get(i));
        Circle customCircle = mData.get(i);
        Log.i("What is the value of", "Radius ---"+customCircle.getCircleRadius());
        circle = new CircleAnimationView(viewGroup.getContext());
        //circle = new CircleAnimationView(viewGroup.getContext(), null, customCircle);
        circle.reDraw(customCircle.getCircleRadius(), customCircle.getCircleSpeed(), customCircle.getCircleColor());
        circle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder recyclerViewHolder = (ViewHolder) viewHolder;
        Circle customCircle = mData.get(i);
        ((ViewHolder) viewHolder).getCustomView().setRadius(Circle.getCircleRadius());
        ((ViewHolder) viewHolder).getCustomView().setSpeed(Circle.getCircleSpeed());
        ((ViewHolder) viewHolder).getCustomView().setColor(Color.GRAY);
        //viewHolder.().setCircleRadius(customCircle.getCircleRadius());
        //recyclerViewHolder.customCircle.setCircleSpeed(customCircle.getCircleSpeed());
        //customCircle.setCircleColor(customCircle.getCircleRadius());
        //recyclerViewHolder.customCircle.setCircleRadius(15);
        //recyclerViewHolder.customCircle.setCircleSpeed(20);
        //Circle testCircle = new Circle(24, 20, Color.BLACK);
        //Log.d("Test", "Value of radius" + customCircle.getCircleRadius());
        //circle.reDraw(customCircle.getCircleRadius(), customCircle.getCircleSpeed(), customCircle.getCircleColor());
        //Log.d("Test", "Value of data.get" + mData.get(i))
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Circle customCircle;
        int circleRadius, circleSpeed, circleColor;

        public ViewHolder(View view) {
            super(view);
            circleRadius = customCircle.getCircleRadius();
            circleSpeed = customCircle.getCircleSpeed();
            circleColor = customCircle.getCircleColor();
        }

        public CircleAnimationView getCustomView() {
            return circle;
        }
    }
}
