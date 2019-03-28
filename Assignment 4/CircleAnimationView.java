package hw.appdev.example.android.assignment4;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class CircleAnimationView extends View {

    float mRadius;
    int mColor;
    float mSpeed;
    Paint mCircle;
    Paint mBodyPaint;
    int mDistance = 0;
    boolean reachedEnd = false;
    boolean reachedStart = true;
    boolean randomCheck = false;

    public CircleAnimationView(Context context) {
        super(context);
    }

    public CircleAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleAnimationView);
        mColor = array.getColor(R.styleable.CircleAnimationView_circleColor, Color.BLUE);
        mRadius = array.getDimension(R.styleable.CircleAnimationView_circleRadius, dpToPx(20));
        mSpeed = array.getDimension(R.styleable.CircleAnimationView_circleSpeed, dpToPx(30));

        array.recycle();
        initPaints();

    }

    public CircleAnimationView(Context context, @Nullable AttributeSet attrs, Circle customCircle) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleAnimationView);
        mColor = array.getColor(R.styleable.CircleAnimationView_circleColor, Color.BLACK);
        mRadius = array.getDimension(Circle.getCircleRadius(), dpToPx(20));
        Log.i("Second constructor", "Radius"+mRadius);
        mSpeed = array.getDimension(Circle.getCircleSpeed(), dpToPx(30));
        Log.i("Second constructor", "Speed"+mSpeed);
        array.recycle();
    }

    public void initPaints() {
        mCircle = new Paint();
        mCircle.setColor(mColor);
        mBodyPaint = new Paint();
        mBodyPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth()-15; //padding
        int height = canvas.getHeight()-10; //padding
        if (randomCheck)
            canvas.drawCircle(220, 33, mRadius, mCircle);
        if (reachedStart) {
            moveCircleX(canvas, width, height);
            if (mDistance>=width-mRadius) {
                reachedStart = false;
                reachedEnd = true;
            }
        }
        if (reachedEnd) {
            moveCircleY(canvas, width, height);
            if (mDistance==10 || mRadius+mDistance<mRadius) {
                reachedEnd = false;
                reachedStart = true;
            }
        }

    }

    private void moveCircleY(Canvas canvas, int width, int height) {
        int distance = width - mDistance;
        canvas.drawRect(0, 0, width, height, mBodyPaint);
        canvas.drawCircle(width-distance, height / 2, mRadius, mCircle);
        mDistance = mDistance - 10;
        //Log.i("Example", "Value of distance: "+mDistance);
        invalidate();
    }

    public void moveCircleX(Canvas canvas, int width, int height) {
        //float distance = mSpeed * 60;
        canvas.drawRect(0, 0, width, height, mBodyPaint);
        canvas.drawCircle(mRadius + mDistance, height / 2, mRadius, mCircle);
        mDistance = mDistance + 10;
        //Log.i("Example", "Value of distance: "+mDistance);
        invalidate();

    }
    public static int dpToPx(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }


    public void reDraw(int circleRadius, int circleSpeed, int circleColor) {

        mSpeed = circleSpeed;
        mRadius = circleRadius;
        mColor = circleColor;
        Log.i("Sup","radius?"+mRadius);
        randomCheck = true;
        //ucheckLayoutParams();
        invalidate();
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }
}
