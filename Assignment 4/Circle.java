package hw.appdev.example.android.assignment4;

import android.graphics.Color;

public class Circle {

    private static int mRadius;
    private static int mColor;
    private static int mSpeed;

    Circle(int radius, int speed, int color) {
        mRadius = radius;
        mSpeed = speed;
        mColor = color;
    }

    public static int getCircleColor() {
        return Color.BLACK;
    }

    public static int getCircleRadius() {
        return mRadius;
    }

    public static int getCircleSpeed() {
        return mSpeed;
    }

    public void setCircleRadius(int circleRadius) {
        mRadius = circleRadius;
    }

    public void setCircleSpeed(int circleSpeed) {
        mSpeed = circleSpeed;
    }
}
