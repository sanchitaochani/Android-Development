package hw.appdev.example.android.assignment2;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class AnimalList extends BaseAdapter{

    Context mContext;
    List<ListRow> mData;

    public AnimalList(Context context, List<ListRow> myData) {
        mContext = context;
        mData = myData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_rows, null);

            ImageView imageView = convertView.findViewById(R.id.img);
            TextView textView = convertView.findViewById(R.id.text);
            ViewHolder viewHolder = new ViewHolder(imageView, textView);
            convertView.setTag(viewHolder);
        }
        ListRow listData = (ListRow) getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag(); //gets the tag of VH
        //reference to the text and image stored in this view
        viewHolder.mImageView.setImageResource(listData.getImg_id());
        viewHolder.mTextView.setText(listData.getMyText());

        ((TextView)convertView.findViewById(R.id.text)).setText(listData.getMyText());
        ((ImageView)convertView.findViewById(R.id.img)).setImageResource(listData.getImg_id());

        //set visibility of corresponding description
        viewHolder.mTextView.setVisibility(listData.isSelected ? View.VISIBLE : View.INVISIBLE);

        //rotate the selected image
        if (listData.isSelected && listData.rotation == 90)
            viewHolder.mImageView.setRotation(viewHolder.mImageView.getRotation() + 90);

        //flip the selected image
        if (listData.isSelected && listData.flip == -1)
            viewHolder.mImageView.setScaleX(viewHolder.mImageView.getScaleX() * -1);

        //move right
        if (listData.isSelected && listData.translate_x_right >= 0)
            viewHolder.mImageView.setTranslationX(viewHolder.mImageView.getTranslationX() + listData.translate_x_right);

        //move left
        if (listData.isSelected && listData.translate_x_left >= 0)
            viewHolder.mImageView.setTranslationX(viewHolder.mImageView.getTranslationX() - listData.translate_x_left);

        //move down
        if (listData.isSelected && listData.translate_y_down >= 0)
            viewHolder.mImageView.setTranslationY(viewHolder.mImageView.getTranslationY() + listData.translate_y_down);

        //move up
        if (listData.isSelected && listData.translate_y_up >= 0)
            viewHolder.mImageView.setTranslationY(viewHolder.mImageView.getTranslationY() - listData.translate_y_up);

        if (listData.isSelected && listData.center >= 0) {
            viewHolder.mImageView.setTranslationY(0);
            viewHolder.mImageView.setTranslationY(0);
        }

        return convertView;
    }



    public static class ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(ImageView imageView, TextView textView) {
            mImageView = imageView;
            mTextView = textView;
        }
    }
}
