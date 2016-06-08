package com.example.steve.mipazrav;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by Steve on 5/4/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Activity activity;

    public ImageAdapter(Activity activity, Context mContext) {
        this.mContext = mContext;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return mThumbIds[i];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;


//        ImageView imageView;
//        if (convertView == null) {
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(screenWidth / 3, screenWidth / 3));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(12, 10, 12, 10);
//
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//
//        return imageView;
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if (convertView == null) {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.image_adapter, null);

            view.textView = (TextView) convertView.findViewById(R.id.text_view1);
            view.imageView = (ImageView) convertView.findViewById(R.id.image_view1);

            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        view.textView.setText(mCatagories[position]);
        view.imageView.setImageResource(mThumbIds[position]);

        return convertView;
    }



    private Integer[] mThumbIds = {R.drawable.parsha, R.drawable.navi,
            R.drawable.megillah, R.drawable.tehillim, R.drawable.holiday,
            R.drawable.prayer, R.drawable.literature, R.drawable.nutshell,
            R.drawable.
                    philosophy, R.drawable.halacha, R.drawable.derechetzchaim,
            R.drawable.peopleintanach, R.drawable.yahrtezeit, R.drawable.pirkeiavot, R.drawable.oraltorah, R.drawable.ramchal, R.drawable.kinyantorah};

    private String[] mCatagories = {"Parsha","Navi","Megillah","Tehillim","Holiday","Prayer","Literature","Nutshell","Philosophy"
    ,"Halacha","Derech Eitz\nChaim","People In Tanach","Yahrtzeit","Pirkei Avot","Oral Torah","Ramchal","Kinyan Torah"};
}

class ViewHolder {
    //The position of this row in list
    int position;

    //The image view for each row
    ImageView imageView;

    //The textView for each row
    TextView textView;
}