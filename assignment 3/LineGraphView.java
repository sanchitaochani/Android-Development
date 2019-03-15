package hw.appdev.example.android.assignment3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LineGraphView extends View {

    Paint mLinePaint;
    Paint mBodyPaint;
    int mAxesColor;
    Paint mPointColor;
    float mRadius;
    int pointColor;
    Paint mMaxPointColor;
    int maxPointColor;
    TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
    int max; //initial value
    int initialHeight = 20;
    //ArrayList<String> stringArray = new ArrayList<>();
    //ArrayList<Integer> intArray = new ArrayList<>();
    String TAG = "Debugging";

    public LineGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineGraphView);
        mAxesColor = array.getColor(R.styleable.LineGraphView_lineColor, Color.BLACK);
        mRadius = array.getDimension(R.styleable.LineGraphView_pointRadius, dpToPx(5));
        pointColor = array.getColor(R.styleable.LineGraphView_pointColor,Color.GRAY);
        maxPointColor = array.getColor(R.styleable.LineGraphView_maxColor, Color.BLUE);
        array.recycle();
        initPaints();
        addData("",0);
    }

    private void initPaints() {
        mBodyPaint = new Paint();
        mBodyPaint.setColor(Color.WHITE);
        mLinePaint = new Paint();
        mLinePaint.setColor(mAxesColor);
        mLinePaint.setStrokeWidth((int) dpToPx(4));
        mPointColor = new Paint();
        mPointColor.setColor(pointColor);
        mMaxPointColor = new Paint();
        mMaxPointColor.setColor(maxPointColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, 0, canvas.getHeight(), mLinePaint);
        canvas.drawLine(0, canvas.getHeight(), canvas.getWidth(), canvas.getHeight(), mLinePaint);

        int height = canvas.getHeight() - 10; //padding
        height = height/initialHeight;
        int width = canvas.getWidth()-30; //padding
        width = width/5;

        treeMap.put("test", 12);
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            int value = entry.getValue();
            Log.i(TAG, "Value of treemap" +value);
            canvas.drawCircle(width/2, height -value, mRadius, mPointColor);
        }

        //canvas.drawCircle(width/2+width, height, dpToPx(5), mPointColor);
        //canvas.drawCircle(width/2+(2*width), height, dpToPx(5), mPointColor);
        //canvas.drawCircle(width/2+(3*width), height, dpToPx(5), mPointColor);
        //canvas.drawCircle(width/2+(4*width), height, dpToPx(5), mPointColor);
    }

    protected void addData(String date, int studentCount) {
//if date is not empty and tree size is less than 5
        if (!date.equalsIgnoreCase("")) {
            if (treeMap.size() == 5) {
                Log.i(TAG, "Size" + treeMap.size());
                treeMap.remove(treeMap.firstKey());
            }
// add values in treemap
            treeMap.put(date, studentCount);
            invalidate();
            Log.i(TAG, "Treemap values???" + treeMap.values());
        }

//check for max value
        //if (max > initialHeight)
            //initialHeight = max + 2;


    }

    /*protected void draw(String date, Integer count) {
        addData(date, count);
        invalidate();

    }*/
    private float dpToPx(int i) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, getContext().getResources().getDisplayMetrics());
    }
}
