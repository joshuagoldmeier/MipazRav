package com.example.steve.mipazrav;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by joshuaegoldmeier on 9/4/2016.
 */
public class FontCache {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}