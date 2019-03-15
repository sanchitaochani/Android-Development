package hw.appdev.example.android.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {

    LineGraphView mLineGraphView;
    EditText mDate, mStudentCount;
    Button mAddDataButton, mClearDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLineGraphView = (LineGraphView) findViewById(R.id.customView);
        mAddDataButton = findViewById(R.id.add);
        mClearDataButton = findViewById(R.id.clear);
        mDate = findViewById(R.id.date_field);
        mStudentCount = findViewById(R.id.count_field);

        final String date = mDate.getText().toString();
        final String studentCount = mStudentCount.getText().toString();

        mAddDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateDate(date) && validateNumber(studentCount)) {
                        mLineGraphView.addData("02/02", 12);
                        mLineGraphView.addData("02/24", 17);
                }
                else
                    Toast.makeText(MainActivity.this, R.string.please_select_valid_date_and_count, Toast.LENGTH_LONG).show();
            }
        });

        mClearDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear graph
            }
        });

    }

    private boolean validateNumber(String studentCount) {
        String PATTERN_COUNT = "[0-9]+";
        return (studentCount.matches(PATTERN_COUNT));
    }

    private boolean validateDate(String date) {
        String DATE_PATTERN = "[1-12]/[1-31]";
        return true;

    }
}
