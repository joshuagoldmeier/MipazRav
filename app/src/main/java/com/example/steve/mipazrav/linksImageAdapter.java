package com.example.steve.mipazrav;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Steve on 5/4/2016.
 */
public class linksImageAdapter extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    private String[] mCatagories = {"Torah", "Hokma", "Megillah", "Music", "Rabbi's Browser",
            "Recommended this Month"};

    public linksImageAdapter(Activity activity, Context mContext) {
        this.mContext = mContext;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return linksImageArray.length;
    }

    @Override
    public Object getItem(int i) {
        return linksImageArray[i];
    }

    @Override
    public long getItemId(int i) {
        return linksImageArray[i];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
//        ImageView imageView;
//        if (convertView == null) {
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(screenWidth/3, screenWidth/3));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(12, 10, 12, 10);
//
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(linksImageArray[position]);
//        return imageView;
//    }
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
        view.imageView.setImageResource(linksImageArray[position]);

        return convertView;
    }

    private Integer[] linksImageArray = {R.drawable.parsha, R.drawable.wisdom,
            R.drawable.megillah, R.drawable.music, R.drawable.rabbi_browser,
            R.drawable.recommended_this_month};

    class ViewHolder {
        //The position of this row in list
        int position;

        //The image view for each row
        ImageView imageView;

        //The textView for each row
        TextView textView;
    }
}