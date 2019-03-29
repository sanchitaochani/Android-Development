package hw.appdev.example.android.assignment4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CircleAnimationView mCircleAnimationView;
    EditText mUserInputRadius;
    EditText mUserInputSpeed;
    Button mColorSelected;
    List<Circle> mCircleData = new ArrayList<Circle>();
    int mRadius, mSpeed;
    int mColor = Adapter.NO_SELECTION;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircleAnimationView = findViewById(R.id.circleView);
        mUserInputRadius = findViewById(R.id.radius_text);
        mUserInputSpeed = findViewById(R.id.speed_text);
        adapter = new RecyclerViewAdapter(this, mCircleData, v -> {
            int position = (int) v.getTag();
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void AddCircleButtonClicked(View view) {
        if (!inputsFilled()) return;
        mRadius = Integer.parseInt(mUserInputRadius.getText().toString());
        if (mRadius<0) {
            Toast.makeText(this, R.string.enter__valid_radius, Toast.LENGTH_LONG).show();
            mUserInputRadius.setText("");
        }
        mSpeed = Integer.parseInt(mUserInputSpeed.getText().toString());
        if (mSpeed<0) {
            Toast.makeText(this, R.string.enter__valid_speed, Toast.LENGTH_LONG).show();
            mUserInputSpeed.setText("");
        }
        mCircleData.add(new Circle(mRadius, mSpeed, mColor));
        adapter.notifyDataSetChanged();
        mUserInputSpeed.setText("");
        mUserInputRadius.setText("");
        mColor = Adapter.NO_SELECTION;
    }

    private boolean inputsFilled() {
        if (mUserInputRadius.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_radius, Toast.LENGTH_LONG).show();
            return false;
        }
        if (mUserInputSpeed.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_speed, Toast.LENGTH_LONG).show();
            return false;
        }
        if (mColor == Adapter.NO_SELECTION) {
            Toast.makeText(this, R.string.select_color, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void redClicked(View view) {
        mColorSelected = findViewById(R.id.red);
        mColor = Color.rgb(255, 0, 0);
    }

    public void orangeClicked(View view) {
        mColorSelected = findViewById(R.id.orange);
        mColor = Color.rgb(255, 165, 0);
    }

    public void greenClicked(View view) {
        mColorSelected = findViewById(R.id.green);
        mColor = Color.rgb(50, 205, 50);
    }

    public void blueClicked(View view) {
        mColorSelected = findViewById(R.id.blue);
        mColor = Color.rgb(0, 0, 255);
    }

    public void yellowClicked(View view) {
        mColorSelected = findViewById(R.id.yellow);
        mColor = Color.rgb(255, 255, 0);
    }
}
