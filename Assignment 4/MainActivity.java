package hw.appdev.example.android.assignment4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
    int mRadius, mSpeed, mColor;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircleData.add(new Circle(10,20,Color.BLACK));
        mCircleAnimationView = findViewById(R.id.circleView);

        mUserInputRadius = findViewById(R.id.radius_text);
        mUserInputSpeed = findViewById(R.id.speed_text);
        mColorSelected = findViewById(R.id.red);

        //String testValue = mUserInputRadius.getText().toString();
        //Log.i("Testing", "Value of radius:"+testValue);

        adapter = new RecyclerViewAdapter(this, mCircleData);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void AddCircleButtonClicked(View view) {
        if (!inputsFilled()) return;
        mRadius = Integer.parseInt(mUserInputRadius.getText().toString());
        mSpeed = Integer.parseInt(mUserInputSpeed.getText().toString());
        //mColor = Integer.parseInt(mColorSelected.getText().toString());

        mCircleData.add(new Circle(mRadius, mSpeed, Color.BLACK));
        adapter.notifyDataSetChanged();
        mUserInputSpeed.setText("");
        mUserInputRadius.setText("");
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
        return true;
    }
}
