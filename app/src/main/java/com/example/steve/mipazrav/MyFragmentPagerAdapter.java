package com.example.steve.mipazrav;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Steve on 5/3/2016.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = new String[] {"Shiurim", "Ask The Rabbi", "Links"};

    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Shiurim();

        } else if (position == 1) {
            return new AskTheRabbiFragment();
        } else if (position == 2) {
            return new links();

        }
        return null;
    }


    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
