package hw.appdev.example.android.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    Button rotate_on_click, flip_on_click;
    ImageView move_right, move_left, move_up, move_down, move_to_original;

    List<ListRow> listData = new ArrayList<ListRow>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        mListView = findViewById(R.id.list);



        //declaring as member variable kept throwing exceptions so I put it down here
        final String[] descriptions = {getString(R.string.bird_description), getString(R.string.cat_description),
                getString(R.string.dog_description), getString(R.string.fish_description),
                getString(R.string.pig_description), getString(R.string.cat_description),
                getString(R.string.cat_description), getString(R.string.cat_description),
                getString(R.string.cat_description)};

        Integer[] img_id = {R.mipmap.bird, R.mipmap.cat, R.mipmap.dog, R.mipmap.ic_fish, R.mipmap.ic_pig,
                            R.mipmap.ic_fish, R.mipmap.ic_fish, R.mipmap.ic_fish, R.mipmap.cat};

        listData = new ArrayList<ListRow>();
        for (int i = 0; i < descriptions.length; i++) {
            ListRow item = new ListRow(img_id[i], descriptions[i]);
            listData.add(item);
        }

        final AnimalList adapter = new AnimalList(this, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                for (int i = 0; i < listData.size(); i++) {
                    listData.get(i).isSelected = position == i ? true : false;
                }
                adapter.notifyDataSetChanged();
            }
        });

        rotate_on_click = findViewById(R.id.button_rotate);

        rotate_on_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            for (int i=0; i<listData.size();i++) {
                if (listData.get(i).isSelected)
                    listData.get(i).rotation = 90;
            }
                adapter.notifyDataSetChanged();
            }
        });

        flip_on_click = findViewById(R.id.button_flip);

        flip_on_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<listData.size();i++) {
                    if (listData.get(i).isSelected)
                        listData.get(i).flip = -1;
                }
                adapter.notifyDataSetChanged();
            }
        });

        move_right = findViewById(R.id.right);
        move_left = findViewById(R.id.left);
        move_down = findViewById(R.id.down);
        move_up = findViewById(R.id.up);
        move_to_original = findViewById(R.id.center);

        move_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).isSelected)
                        listData.get(i).translate_x_right = dpToPx(10);
                }
                adapter.notifyDataSetChanged();
            }
        });

        move_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).isSelected)
                        listData.get(i).translate_x_left = dpToPx(10);
                }
                adapter.notifyDataSetChanged();

            }
        });

        move_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).isSelected)
                        listData.get(i).translate_y_up = dpToPx(10);
                }
                adapter.notifyDataSetChanged();

            }
        });

        move_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).isSelected)
                        listData.get(i).translate_y_down = dpToPx(10);
                }
                adapter.notifyDataSetChanged();

            }
        });

        move_to_original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).isSelected)
                        listData.get(i).center = 1;
                }
                adapter.notifyDataSetChanged();

            }
        });
    }
    private float dpToPx(int dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }
}
